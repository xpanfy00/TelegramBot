package com.carter.tgbot.service;

import com.carter.tgbot.dto.ProjectDto;
import com.carter.tgbot.repository.ProjectRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class SaveKworksToDB {

    private final ProjectRepository projectRepository;

    @Autowired
    public SaveKworksToDB(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String save(String urlString) throws IOException, InterruptedException, ExecutionException {

        // Получаем данные о последней странице
        var jsonNode = KworkApi.sendPostRequest(urlString + "1");
        JsonNode wantsArray = jsonNode.path("data").path("pagination");
        int lastPage = wantsArray.path("last_page").asInt();

        // Список для хранения всех проектов
        List<ProjectDto> projects = new ArrayList<>();

        // Создаём ExecutorService для параллельной загрузки
        ExecutorService executorService = Executors.newFixedThreadPool(lastPage);
        List<Future<List<ProjectDto>>> futures = new ArrayList<>();

        // Скачиваем данные для каждой страницы в отдельных потоках
        for (int i = 1; i <= lastPage; i++) {
            int finalI = i; // для использования в лямбде
            futures.add(executorService.submit(() -> {
                List<ProjectDto> projectDtos = KworkApi.processJson(KworkApi.sendPostRequest(urlString + finalI));
                System.out.println("Page " + finalI + " has been added to list projects");
                return projectDtos;
            }));
        }

        // Ожидаем завершения всех потоков и собираем результаты
        for (Future<List<ProjectDto>> future : futures) {
            projects.addAll(future.get());
        }

        // Завершаем работу ExecutorService
        executorService.shutdown();

        // Сохраняем проекты в базе данных
        for (ProjectDto projectDto : projects) {
            var exists = projectRepository.findProjectDtoById(projectDto.getId());
            if (exists.isEmpty()) {
                System.out.println("Project with id " + projectDto.getId() + " doesn't exist, saving...");
                projectRepository.save(projectDto);
            } else {
                System.out.println("Project with id " + projectDto.getId() + " already exists.");
            }
        }

        return "ok";
    }
}
