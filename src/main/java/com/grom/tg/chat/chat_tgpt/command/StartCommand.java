package com.grom.tg.chat.chat_tgpt.command;

import com.grom.tg.chat.chat_tgpt.bot.MyTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class StartCommand implements BotCommand{


    private final MyTelegramBot myTelegramBot;

    public StartCommand(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;
    }


    @Override
    public void execute(Update update) {
        String response = null;

        response = "Hallo, haw u doin?" + update.getMessage().getFrom();;

        myTelegramBot.sendMessage(update.getMessage().getChatId(), response);

    }

}
