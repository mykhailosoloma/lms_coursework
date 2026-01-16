-- 1. Пользователи (Пароль для всех: "password")
-- Хеш: $2a$10$X7.G1. ... (это зашифрованное слово "password")

-- Инструктор
INSERT INTO users (email, first_name, last_name, password_hash, role)
VALUES ('instructor@kpi.ua', 'Иван', 'Петров', '$2a$10$wW.q..p9/w.w.../exampleHashPlaceHolder', 'INSTRUCTOR');

-- Студент 1
INSERT INTO users (email, first_name, last_name, password_hash, role)
VALUES ('student1@kpi.ua', 'Алексей', 'Сидоров', '$2a$10$wW.q..p9/w.w.../exampleHashPlaceHolder', 'STUDENT');

-- Студент 2
INSERT INTO users (email, first_name, last_name, password_hash, role)
VALUES ('student2@kpi.ua', 'Мария', 'Коваленко', '$2a$10$wW.q..p9/w.w.../exampleHashPlaceHolder', 'STUDENT');


-- 2. Курсы (Связываем с инструктором через подзапрос, чтобы не гадать ID)
INSERT INTO courses (title, description, category, instructor_id)
VALUES (
    'Java Enterprise (Spring Boot)',
    'Полный курс по разработке на Spring Boot 3',
    'Programming',
    (SELECT user_id FROM users WHERE email = 'instructor@kpi.ua')
);

INSERT INTO courses (title, description, category, instructor_id)
VALUES (
    'Основы Баз Данных (PostgreSQL)',
    'Изучение SQL, проектирование схем и нормализация',
    'Database',
    (SELECT user_id FROM users WHERE email = 'instructor@kpi.ua')
);


-- 3. Модули (для курса Java)
INSERT INTO modules (title, order_index, course_id)
VALUES
('Введение в Spring', 1, (SELECT course_id FROM courses WHERE title = 'Java Enterprise (Spring Boot)')),
('Работа с базой данных (JPA)', 2, (SELECT course_id FROM courses WHERE title = 'Java Enterprise (Spring Boot)'));


-- 4. Уроки (Lessons)
-- Уроки для модуля "Введение в Spring"
INSERT INTO lessons (title, content_type, order_index, module_id)
VALUES
('Что такое Dependency Injection', 'VIDEO', 1, (SELECT module_id FROM modules WHERE title = 'Введение в Spring')),
('Структура проекта Spring Boot', 'TEXT', 2, (SELECT module_id FROM modules WHERE title = 'Введение в Spring'));

-- Уроки для модуля "Работа с базой данных"
INSERT INTO lessons (title, content_type, order_index, module_id)
VALUES
('Сущности и аннотации @Entity', 'TEXT', 1, (SELECT module_id FROM modules WHERE title = 'Работа с базой данных (JPA)')),
('Репозитории и JpaRepository', 'VIDEO', 2, (SELECT module_id FROM modules WHERE title = 'Работа с базой данных (JPA)'));


-- 5. Зачисление студентов на курс (Enrollments)
INSERT INTO enrollments (user_id, course_id)
VALUES (
    (SELECT user_id FROM users WHERE email = 'student1@kpi.ua'),
    (SELECT course_id FROM courses WHERE title = 'Java Enterprise (Spring Boot)')
);


-- 6. Прогресс (Progress)
-- Студент 1 прошел первый урок
INSERT INTO progress (enrollment_id, lesson_id, status, last_accessed)
VALUES (
    (SELECT enrollment_id FROM enrollments WHERE user_id = (SELECT user_id FROM users WHERE email = 'student1@kpi.ua')),
    (SELECT lesson_id FROM lessons WHERE title = 'Что такое Dependency Injection'),
    'COMPLETED',
    CURRENT_TIMESTAMP
);