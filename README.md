## Курсовая работа

# Video Library Platform

## 📌 О проекте

Video Library Platform - это полнофункциональное веб-приложение для управления видео контентом с возможностью:
- Покупки и аренды видео
- Категоризации контента
- Потоковой передачи видео
- Администрирования библиотеки

## 🛠 Технологический стек

**Backend:**
- Java 17
- Spring Boot 
- Spring Security
- Spring Data JPA
- Lombok

**Базы данных:**
- PostgreSQL (основное хранилище)
- Redis (управление сессиями)

**Инфраструктура:**
- Docker
- Docker Compose

## 🚀 Быстрый старт

### Требования:
- Docker 24.0+
- Docker Compose 2.20+
- 4GB+ свободной памяти

### Запуск:
```bash
git clone https://github.com/your-repo/video-library.git
cd video-library
docker compose up --build
