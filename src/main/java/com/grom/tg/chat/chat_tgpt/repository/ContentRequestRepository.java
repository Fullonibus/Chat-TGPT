package com.grom.tg.chat.chat_tgpt.repository;

import com.grom.tg.chat.chat_tgpt.entity.gemini.ContentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRequestRepository extends MongoRepository<ContentRequest, String> {
    public boolean existsByChatId(Long chatId);

    public ContentRequest findByChatId(Long chatId);
}
