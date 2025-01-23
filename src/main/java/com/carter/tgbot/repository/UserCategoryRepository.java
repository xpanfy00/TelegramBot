package com.carter.tgbot.repository;

import com.carter.tgbot.dto.Category;
import com.carter.tgbot.dto.TelegramUser;
import com.carter.tgbot.dto.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    List<UserCategory> findByUser(TelegramUser user);
    List<UserCategory> findByCategory(Category category);
    List<UserCategory> findByUserAndCategory(TelegramUser user, Category category);
}
