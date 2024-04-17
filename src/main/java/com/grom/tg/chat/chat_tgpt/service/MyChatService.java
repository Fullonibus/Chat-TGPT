package com.grom.tg.chat.chat_tgpt.service;

import com.grom.tg.chat.chat_tgpt.entity.MyChat;
import com.grom.tg.chat.chat_tgpt.entity.MyUser;
import com.grom.tg.chat.chat_tgpt.repository.ChatRepository;
import com.grom.tg.chat.chat_tgpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class MyChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;



    public MyChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertNewChat(Message message) {
        Chat chat = message.getChat();
        if(!chatRepository.existsById(chat.getId())) {
//            MyUser user = userRepository.findByUserName(chat.getUserName());
            MyUser user = userRepository.findByUserId(message.getFrom().getId());
            chatRepository.save(new MyChat(chat.getId(), user));
            System.out.println("New Chat created: " + chat.getId());
        }

    }

}
