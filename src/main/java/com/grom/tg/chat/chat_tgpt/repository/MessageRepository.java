package com.grom.tg.chat.chat_tgpt.repository;

import com.grom.tg.chat.chat_tgpt.entity.MyMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<MyMessage, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE MyMessage m SET m.isConversation = false ")
    void updateIsConversationToFalse();

    boolean existsByMessageId(Integer messageId);

}
