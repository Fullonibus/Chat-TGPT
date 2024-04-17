package com.grom.tg.chat.chat_tgpt.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;

@Entity
@Table(name = "_tg_user")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class MyUser implements Serializable {
    @Id
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;

}
