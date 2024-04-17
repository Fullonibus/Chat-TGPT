package com.grom.tg.chat.chat_tgpt.service;

import com.grom.tg.chat.chat_tgpt.entity.MyUser;
import com.grom.tg.chat.chat_tgpt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class MyUserService {

    private final UserRepository userRepository;

    public MyUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void insertNewUser(User user) {
        if (!userRepository.existsByUserId(user.getId())) {
            userRepository.save(new MyUser(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName()));
            System.out.println("New user created: " + user.getId());
        }
    }
}
