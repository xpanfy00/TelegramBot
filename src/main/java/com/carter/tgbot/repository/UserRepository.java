package com.carter.tgbot.repository;

import com.carter.tgbot.dto.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<TelegramUser, Long> {

    List<TelegramUser> findTelegramUserById(long id);
    List<TelegramUser> findTelegramUserByName(String name);
}
