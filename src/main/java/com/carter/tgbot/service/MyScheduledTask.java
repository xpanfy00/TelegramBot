package com.carter.tgbot.service;

import com.carter.tgbot.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class MyScheduledTask {


    private final BotConfig botConfig;
    @Autowired
    private SaveKworksToDB saveKworksToDB;

    public MyScheduledTask(BotConfig botConfig) {
        this.botConfig = botConfig;
    }



    @Scheduled(cron = "1 0/25 * * *  ?")
    public void executeCronTask() throws IOException, ExecutionException, InterruptedException {


        String url = "https://kwork.ru/projects?c=all&page=";
        saveKworksToDB.save(url);
        System.out.println("Задача по расписанию (cron): " + System.currentTimeMillis());
    }
}
