package com.carter.tgbot.bot;

import com.carter.tgbot.config.BotConfig;
import com.carter.tgbot.repository.ProjectRepository;
import com.carter.tgbot.repository.UserRepository;
import com.carter.tgbot.service.ButtonServise;
import com.carter.tgbot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class TelegramBot implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private ButtonServise buttonServise;



    private final BotConfig botConfig;
    private final TelegramClient telegramClient;
    private final CommandHandler commandHandler = new CommandHandler();
    private final ProjectRepository projectRepository;

    public TelegramBot(BotConfig botConfig, UserRepository userRepository, ProjectRepository projectRepository) {
        this.botConfig = botConfig;
        this.projectRepository = projectRepository;
        telegramClient = new OkHttpTelegramClient(getBotToken());
        this.userRepository = userRepository;
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    public void sendMessageToUSer(String chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)        // Установите chatId
                .text(text)     // Установите текст сообщения
                .build();
        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void consume(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            if (userRepository.findTelegramUserById(user.getId()).isEmpty()) {
                userService.addUser(user);
            }
            if (!userRepository.findTelegramUserByName(user.getUserName()).get(0).getName().equals(user.getUserName())){
                userService.updateUser(user.getId(), user);
            }
            if(text.startsWith("/kwork")){

                var result = projectRepository.findByDescriptionContaining("java");

                for (var res : result) {
                    sendMessageToUSer(user.getId().toString(), res.toString());
                }


            }

            if (text.startsWith("/category")) {

                buttonServise.sendCategoryButtons(chatId, telegramClient);

            }

            if (text.startsWith("/")) {
                SendMessage message = commandHandler.handleCommand(text, chatId);
                try {
                    telegramClient.execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Ошибка при обработке команды" + e);
                }
            }
        }
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }
}
