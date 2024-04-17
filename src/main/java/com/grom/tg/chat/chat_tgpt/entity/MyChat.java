package com.grom.tg.chat.chat_tgpt.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;

@Entity
@Table(name = "_tg_chat")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class MyChat implements Serializable {

    @Id
    @NotNull
    private Long chatId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyUser user;
}
