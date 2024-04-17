package com.grom.tg.chat.chat_tgpt.bot;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grom.tg.chat.chat_tgpt.command.BotCommand;
import com.grom.tg.chat.chat_tgpt.entity.MyChat;
import com.grom.tg.chat.chat_tgpt.entity.MyUser;
import com.grom.tg.chat.chat_tgpt.entity.gemini.ContentRequest;
import com.grom.tg.chat.chat_tgpt.handler.BotHandler;
import com.grom.tg.chat.chat_tgpt.repository.ChatRepository;
import com.grom.tg.chat.chat_tgpt.repository.MessageRepository;
import com.grom.tg.chat.chat_tgpt.repository.UserRepository;
import com.grom.tg.chat.chat_tgpt.service.GeminiService;
import com.grom.tg.chat.chat_tgpt.service.MyChatService;
import com.grom.tg.chat.chat_tgpt.service.MyMessageService;
import com.grom.tg.chat.chat_tgpt.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;

import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.nio.channels.Channel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MyTelegramBot  extends TelegramLongPollingBot {

    @Value("${bot.name.value}")
    private String botNameValue;

    @Value("${model.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyUserService userService;

    @Autowired
    private MyChatService chatService;

    @Autowired
    private MyMessageService messageService;

    @Autowired
    private GeminiService geminiService;

    private final BotHandler botHandler;

    @Autowired
    private MessageRepository messageRepository;

    public MyTelegramBot(String botToken, BotHandler botHandler) {
        super(botToken);
        this.botHandler = botHandler;
        this.botHandler.setBot(this);
        System.out.println(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = message.getText();
            User user = message.getFrom();
            Long chatId = message.getChatId();
            //DB user setup
            userService.insertNewUser(user);
            chatService.insertNewChat(message);

            //check if message is command
            BotCommand command = botHandler.getCommandMap().get(messageText.split(" ")[0]); // /start
            if (command != null) {

                command.execute(update);
            }
            //if not - send text to gemini
            else {
                //save log
                messageService.insertMessage(message);

                //get an answer from gemini
                String answer = geminiService.getAnswer(chatId, messageText);

                //send to user via tg
                sendMessage(chatId, answer);
                System.out.println(answer);
            }
        }
    }

    public void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botNameValue;
    }
}
