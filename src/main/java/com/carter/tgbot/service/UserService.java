package com.carter.tgbot.service;

import com.carter.tgbot.dto.TelegramUser;
import com.carter.tgbot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    TelegramUser userToSave = new TelegramUser();

    // Добавление нового пользователя
    public TelegramUser addUser(User user) {
        userToSave.setId(user.getId());
        userToSave.setChatID(user.getId().toString());
        userToSave.setName(user.getUserName());
        return userRepository.save(userToSave);
    }

    // Обновление существующего пользователя
    public TelegramUser updateUser(Long userId, User updatedUser) {
        TelegramUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedUser.getUserName());
        // Обновить другие поля, если необходимо
        return userRepository.save(user);
    }

    // Удаление пользователя
    public void deleteUser(Long userId) {
        TelegramUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    // Получение пользователя по ID
    public TelegramUser getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
