package com.grom.tg.chat.chat_tgpt.entity;

import jakarta.persistence.*;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.Chat;

import java.io.Serializable;

@Entity
@Table(name = "_tg_message")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class MyMessage implements Serializable {

    @Id
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser from;
    private Integer date;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private MyChat chat;
    private String text;
    private boolean isConversation;

}
