# LMS System - Система управління навчанням

## Огляд проекту

Даний проєкт є курсовою роботою з дисципліни "Бази даних" та реалізує повноцінну систему управління навчанням (LMS).

## Призначення та предметна область

Метою проєкту є створення надійної та ефективної бази даних та програмного інтерфейсу для автоматизації процесів електронного навчання.

Система охоплює такі основні предметні області:

- **Управління користувачами**: Реєстрація студентів та викладачів, зберігання їх профілів, ролей та прав доступу.
- **Керування курсами**: Створення, редагування та видалення курсів, модулів та уроків.
- **Навчальний процес**: Зарахування студентів на курси, відслідковування прогресу навчання.
- **Аналітика та звітність**: Збір та аналіз даних про успішність студентів, популярність курсів та ефективність викладачів.

## Використані технології

Проєкт використовує сучасний стек технологій для забезпечення надійності, гнучкості та масштабованості:

- **База даних**: PostgreSQL
- **ORM**: Spring Data JPA + Hibernate
- **Бекенд**: Java 21, Spring Boot 3.4
- **Документація API**: Swagger/OpenAPI
- **Контейнеризація**: Docker та Docker Compose для легкого розгортання середовища.

## Опис схеми бази даних

Система включає 7 основних таблиць, пов'язаних між собою:

- **users (Користувачі)**: Студенти, викладачі, адміністратори системи
- **courses (Курси)**: Навчальні курси з описом та категоріями
- **modules (Модулі)**: Структурні одиниці курсу
- **lessons (Уроки)**: Матеріали, які входять до модулів
- **enrollments (Зарахування)**: Зв'язки між студентами та курсами
- **progress (Прогрес)**: Відстеження проходження уроків студентами
- **files (Файли)**: Навчальні матеріали та ресурси

Детальна інформація про схему - docs/schema.md

## Інструкції з налаштування та запуску

Для коректної роботи системи на локальній машині необхідно мати встановлені:

- Java (версії 21)
- Maven (для збірки проєкту)
- Docker та Docker Compose (для бази даних)

### 1. Клонування репозиторію

```
git clone https://github.com/mykhailosoloma/lms_coursework
cd lms-system
```

### 2. Налаштування змінних середовища

Створіть файл application-local.properties у каталозі src/main/resources/ або використовуйте змінні середовища для налаштування підключення до бази даних:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/lms_db
spring.datasource.username=postgres
spring.datasource.password=password123
```

### 3. Запуск бази даних (Docker)

Запустіть контейнер з PostgreSQL:

```
docker-compose up -d
```

Переконайтеся, що контейнер запущено:

```
docker ps
```

### 4. Збірка та запуск проєкту

Зберіть проєкт за допомогою Maven:

```
mvn clean install
```

Запустіть додаток:

```
java -jar target/lms-system-0.0.1-SNAPSHOT.jar
```

Або використовуйте команду Maven:

```
mvn spring-boot:run
```

Сервер буде доступний за адресою: http://localhost:8081.

API документація (Swagger UI) доступна за адресою: http://localhost:8081/swagger-ui.html

## Запуск тестів

Проєкт містить різноманітні тести для перевірки функціональності. Перед запуском переконайтеся, що база даних активна.

Запуск усіх тестів:

```
mvn test
```

Запуск тестів конкретного класу:

```
mvn test -Dtest=UserServiceTest
```

Запуск конкретного тесту:

```
mvn test -Dtest=UserServiceTest#testRegisterUser_Success
```

## Структура проєкту

```
lms-system/
├── docs/ # Документація проєкту
│ ├── schema.md # Опис схеми бази даних
│
├── src/ # Вихідний код
│ ├── main/
│ │ ├── java/edu/kpi/lms_system/
│ │ │ ├── config/ # Конфігурація Spring
│ │ │ │ └── SwaggerConfig.java
│ │ │ │
│ │ │ ├── controller/ # REST контролери
│ │ │ │ ├── CourseController.java
│ │ │ │ ├── EnrollmentController.java
│ │ │ │ ├── LessonController.java
│ │ │ │ ├── ModuleController.java
│ │ │ │ ├── ProgressController.java
│ │ │ │ └── UserController.java
│ │ │ │
│ │ │ ├── dto/ # Об'єкти передачі даних
│ │ │ │ ├── mapping/ # Маппінг між DTO та моделями
│ │ │ │ │ ├── CourseMapper.java
│ │ │ │ │ ├── EnrollmentMapper.java
│ │ │ │ │ ├── LessonMapper.java
│ │ │ │ │ ├── ModuleMapper.java
│ │ │ │ │ ├── ProgressMapper.java
│ │ │ │ │ └── UserMapper.java
│ │ │ │ │
│ │ │ │ ├── request/ # DTO для запитів
│ │ │ │ │ ├── CourseRequestDto.java
│ │ │ │ │ ├── EnrollmentRequestDto.java
│ │ │ │ │ ├── LessonRequestDto.java
│ │ │ │ │ ├── ModuleRequestDto.java
│ │ │ │ │ ├── ProgressRequestDto.java
│ │ │ │ │ └── UserRequestDto.java
│ │ │ │ │
│ │ │ │ └── response/ # DTO для відповідей
│ │ │ │ ├── CourseResponseDto.java
│ │ │ │ ├── EnrollmentResponseDto.java
│ │ │ │ ├── LessonResponseDto.java
│ │ │ │ ├── ModuleResponseDto.java
│ │ │ │ ├── ProgressResponseDto.java
│ │ │ │ └── UserResponseDto.java
│ │ │ │
│ │ │ ├── model/ # JPA сутності
│ │ │ │ ├── Course.java
│ │ │ │ ├── Enrollment.java
│ │ │ │ ├── Lesson.java
│ │ │ │ ├── Module.java
│ │ │ │ ├── Progress.java
│ │ │ │ └── User.java
│ │ │ │
│ │ │ ├── repository/ # Репозиторії доступу до даних
│ │ │ │ ├── CourseRepository.java
│ │ │ │ ├── EnrollmentRepository.java
│ │ │ │ ├── LessonRepository.java
│ │ │ │ ├── ModuleRepository.java
│ │ │ │ ├── ProgressRepository.java
│ │ │ │ └── UserRepository.java
│ │ │ │
│ │ │ ├── service/ # Бізнес-логіка
│ │ │ │ ├── CourseService.java
│ │ │ │ ├── EnrollmentService.java
│ │ │ │ ├── LessonService.java
│ │ │ │ ├── ModuleService.java
│ │ │ │ ├── ProgressService.java
│ │ │ │ └── UserService.java
│ │ │ │
│ │ │ └── LmsSystemApplication.java # Точка входу в додаток
│ │ │
│ │ └── resources/
│ │ ├── application.properties # Основні налаштування
│ │ └── db/migration/ # Міграції Flyway
│ │ └── V1__Init_DB.sql # Початкова схема бази даних
│ │
│ └── test/ # Тести
│ ├── java/edu/kpi/lms_system/
│ │ ├── controller/
│ │ │ └── CourseControllerTest.java
│ │ │
│ │ │
│ │ └── service/
│ │ ├── CourseServiceTest.java
│ │ ├── ProgressServiceTest.java
│ │ └── UserServiceTest.java
│ │
│ └── resources/
│ ├── application-test.properties # Тестові налаштування
│
├── .dockerignore # Виключення для Docker
├── .gitignore # Ігнорування Git
├── docker-compose.yml # Конфігурація Docker контейнерів
├── Dockerfile # Конфігурація образу Java
├── mvnw # Maven Wrapper скрипт
├── mvnw.cmd # Maven Wrapper скрипт для Windows
├── pom.xml # Залежності та конфігурація Maven
└── README.md # Основна документація
```

## Основні функції системи

### Управління користувачами

- Реєстрація нових користувачів (студентів, викладачів, адміністраторів)
- Пошук користувачів за ім'ям або іншими критеріями
- Статистика розподілу користувачів за ролями

### Управління курсами

- Створення нових курсів з модулями
- Пошук курсів за категоріями
- Аналітика ефективності викладачів
- Виявлення курсів без студентів

### Зарахування та навчальний процес

- Зарахування студентів на курси
- Відстеження прогресу навчання
- Позначення уроків як завершених
- Скидання прогресу за потреби

### Аналітика та звітність

- Аналіз кількості незавершених уроків
- Звіти про останні зарахування
- Статистика популярності курсів

## Технічні особливості

- **Транзакційність**: Всі операції з базою даних виконуються в транзакціях
- **Валідація даних**: Всі вхідні дані перевіряються перед збереженням
- **REST API**: Повноцінний REST API з використанням HTTP методів
- **Документація API**: Автоматична генерація документації Swagger/OpenAPI
- **Міграції БД**: Використання Flyway для контрольованих змін схеми БД
- **Тестування**: Модульні та інтеграційні тести для перевірки функціональності