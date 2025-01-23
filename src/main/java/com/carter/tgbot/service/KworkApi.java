package com.carter.tgbot.service;

import com.carter.tgbot.dto.ProjectDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KworkApi {

    public static JsonNode sendPostRequest(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);


        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            String jsonResponse = response.toString();


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            return jsonNode;

        }
    }


    public static List<ProjectDto> processJson(JsonNode jsonNode) {
        List<ProjectDto> projectDtos = new ArrayList<>();

        JsonNode wantsArray = jsonNode.path("data").path("pagination").path("data");
        if (wantsArray.isArray()) {
            for (JsonNode item : wantsArray) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setId(item.path("id").asInt());
                projectDto.setStatus(item.path("status").asText());
                projectDto.setDateCreate(item.path("wantDates").path("dateCreate").asText());
                projectDto.setDateActive(item.path("wantDates").path("dateActive").asText());
                projectDto.setDateExpire(item.path("wantDates").path("dateExpire").asText());
                projectDto.setWantUserGetProfileUrl(item.path("wantUserGetProfileUrl").asText());
                projectDto.setPriceLimit(item.path("priceLimit").asInt());
                projectDto.setPossiblePriceLimit(item.path("possiblePriceLimit").asInt());
                projectDto.setParentCategoryId(item.path("category_id").asText());
                projectDto.setDescription(item.path("description").asText());
                projectDto.setKworkCount(item.path("kwork_count").asText());
                projectDto.setName(item.path("name").asText());
                projectDto.setViewsDirty(item.path("views_dirty").asInt());
                projectDto.setTimeLeft(item.path("timeLeft").asText());

                projectDtos.add(projectDto);
            }
        }

        return projectDtos;
    }

}
