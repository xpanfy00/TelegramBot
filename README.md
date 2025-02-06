# Telegram Bot in Java (Spring Boot)

## Description

This project is a **Telegram bot** developed in **Java** using **Spring Boot**. The bot interacts with various APIs, processes user commands, and stores data in a PostgreSQL database.

## Technologies

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **TelegramBots 8.0.0**
- **PostgreSQL**
- **Maven**

## Project Structure 
 ```├── TgbotApplication.java # Main Spring Boot class.
 ├── bot/
 │
 ├── TelegramBot.java # Main bot class.
 │
 └── CommandHandler.java # Command handler for processing bot commands.
 ├── config/
 │
 └── BotConfig.java # Bot configuration.
 ├── dto/ # DTO classes for data transfer.
 ├── repository/ # Interfaces for database operations.
 ├── service/ # Service classes (API clients, scheduled tasks, etc.).
 └── resources/
 └── application.yml # Application settings.
```
## Installation and Run
Clone the repository:
```git clone <repo-url>```

Navigate to the project folder:
```cd tgbot```
Set up your database connection details in application.yml.

Build and run the application:
```./mvnw spring-boot:run```

## Usage
Once the bot is started, it will automatically connect to the Telegram API.
You can send commands, which will be processed by the ```CommandHandler.java.```

## Contacts
Author: xpanfy00

