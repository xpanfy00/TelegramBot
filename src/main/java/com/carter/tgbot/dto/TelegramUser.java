package com.carter.tgbot.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelegramUser {

    @Id
    private long id;

    private String name;

    @Column(unique = true)
    private String chatID;

}
