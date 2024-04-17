package com.grom.tg.chat.chat_tgpt.repository;

import com.grom.tg.chat.chat_tgpt.entity.MyChat;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<MyChat, Long> {

    @Cacheable(value = "caches", key = "#chatId")
    MyChat findByChatId(Long chatId);

    @CacheEvict(value = "caches", key = "#chatId")
    public void deleteByChatId(Long chatId);



}
