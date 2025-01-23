package com.carter.tgbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ButtonServise {

    public void sendCategoryButtons(long chatId, TelegramClient telegramClient) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text("Выберите категорию:")
                .replyMarkup(createCategoryButtons()) // Добавляем кнопки
                .build();

        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка при отправке кнопок: " + e.getMessage());
        }
    }

    private InlineKeyboardMarkup createCategoryButtons() {

        List<InlineKeyboardRow> keyboard = new ArrayList<>();

        // Список категорий
        List<String> categories = Arrays.asList(
                "Дизайн",
                "Разработка и IT",
                "Тексты и переводы",
                "SEO и трафик",
                "Соцсети и реклама",
                "Аудио, видео, съемка",
                "Бизнес и жизнь"
        );

        // Создание кнопок для каждой категории
        for (String category : categories) {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(category)
                    .callbackData("CATEGORY_" + category.replace(" ", "_")) // Уникальные данные для каждой кнопки
                    .build();

            // Создаем строку клавиатуры для кнопки
            InlineKeyboardRow row = new InlineKeyboardRow();
            row.add(button);

            // Добавляем строку в клавиатуру
            keyboard.add(row);
        }

        // Создание разметки клавиатуры
        return InlineKeyboardMarkup.builder()
                .keyboard(keyboard) // Устанавливаем список строк клавиатуры
                .build();
    }

}
