Telegram Bot in Java (Spring Boot)

Description

This project is a Telegram bot developed in Java using Spring Boot. The bot interacts with APIs, processes user commands, and stores data in a database.

Technologies

Java 17

Spring Boot

Spring Data JPA

TelegramBots 8.0.0

PostgreSQL 

Maven

Project Structure

TgbotApplication.java — Main Spring Boot class.

bot/TelegramBot.java — Main bot class.

bot/CommandHandler.java — Command handler.

config/BotConfig.java — Bot configuration.

dto/ — DTO classes for data transfer.

repository/ — Interfaces for database operations.

service/ — Service classes, including API clients and scheduled tasks.

resources/application.yml — Application settings.

Installation and Run

Clone the repository:

git clone <repo-url>

Navigate to the project folder:

cd tgbot

Set up database connection details in application.yml.

Build and run the application:

./mvnw spring-boot:run

Usage

Once started, the bot automatically connects to the Telegram API. You can send commands, which are processed in CommandHandler.java.

Contacts

Author: xpanfy00
