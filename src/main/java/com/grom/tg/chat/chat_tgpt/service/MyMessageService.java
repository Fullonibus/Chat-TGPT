package com.grom.tg.chat.chat_tgpt.service;

import com.grom.tg.chat.chat_tgpt.entity.MyChat;
import com.grom.tg.chat.chat_tgpt.entity.MyMessage;
import com.grom.tg.chat.chat_tgpt.entity.MyUser;
import com.grom.tg.chat.chat_tgpt.repository.ChatRepository;
import com.grom.tg.chat.chat_tgpt.repository.MessageRepository;
import com.grom.tg.chat.chat_tgpt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class MyMessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    public MyMessageService(MessageRepository messageRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertMessage(Message message) {
        MyChat chat = chatRepository.findByChatId(message.getChatId());
        MyUser from = userRepository.findByUserId(message.getFrom().getId());
        if (!messageRepository.existsByMessageId(message.getMessageId())) {
            MyMessage myMessage = MyMessage.builder()
                    .messageId(message.getMessageId())
                    .chat(chat)
                    .from(from)
                    .date(message.getDate())
                    .text(message.getText())
                    .isConversation(true)
                    .build();

            System.out.println(myMessage.getMessageId());
            System.out.println(myMessage.getChat().getChatId());
            System.out.println(myMessage.getFrom().getUserId());

            messageRepository.save(myMessage);
        }

        else {
            System.out.println("ТРЕВОГА, У НАС ПАЛЁНЫЙ АЙДИ?!?!?!    " + message.getMessageId());
        }


    }
}
