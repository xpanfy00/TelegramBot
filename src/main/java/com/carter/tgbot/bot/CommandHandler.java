package com.carter.tgbot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

public class CommandHandler {

    public SendMessage handleCommand(String command, long chatId) {
        switch (command) {
            case "/start":
                return SendMessage.builder()
                        .chatId(chatId)
                        .text("Dobro pozalovat")
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
            case "/help":
                return SendMessage.builder()
                        .chatId(chatId)
                        .text("Список доступных команд:\n/start - Начало\n/help - Помощь")
                        .build();
            default:
                return SendMessage.builder()
                        .chatId(chatId)
                        .text("Неизвестная команда.")
                        .build();
        }
    }
}
