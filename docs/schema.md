# Детальний опис таблиць

## 1. users (Користувачі)
Зберігає дані про всіх учасників системи: студентів, викладачів та адміністраторів.

| Стовпець | Тип | Обмеження | Опис                                         |
|----------|-----|-----------|----------------------------------------------|
| user_id | BIGSERIAL | PK | Унікальний ідентифікатор користувача.        |
| email | VARCHAR(255) | UNIQUE, NOT NULL | Електронна пошта (логін).                    |
| first_name | VARCHAR(255) | | Ім'я користувача.                            |
| last_name | VARCHAR(255) | | Прізвище користувача.                        |
| password_hash | VARCHAR(255) | | Пароль .                                     |
| role | VARCHAR(50) | | Роль у системі (STUDENT, INSTRUCTOR, ADMIN). |
| created_at | TIMESTAMP | DEFAULT NOW() | Дата реєстрації.                             |

## 2. courses (Курси)
Основна сутність навчання. Створюється викладачем.

| Стовпець | Тип | Обмеження | Опис |
|----------|-----|-----------|------|
| course_id | BIGSERIAL | PK | Унікальний ідентифікатор курсу. |
| title | VARCHAR(255) | NOT NULL | Назва курсу. |
| description | TEXT | | Детальний опис курсу. |
| category | VARCHAR(255) | | Категорія (напр. "IT", "Math"). |
| instructor_id | BIGINT | FK | Посилання на users.user_id. |
| created_at | TIMESTAMP | DEFAULT NOW() | Дата створення. |

**Зв'язок:** instructor_id посилається на users(user_id).  
**Видалення:** ON DELETE CASCADE — при видаленні викладача курс видаляється.

## 3. modules (Модулі)
Структурні блоки, з яких складається курс.

| Стовпець | Тип | Обмеження | Опис |
|----------|-----|-----------|------|
| module_id | BIGSERIAL | PK | Унікальний ідентифікатор модуля. |
| title | VARCHAR(255) | NOT NULL | Назва модуля. |
| order_index | INT | | Порядковий номер модуля в курсі. |
| course_id | BIGINT | FK, NOT NULL | Посилання на courses.course_id. |

**Зв'язок:** course_id посилається на courses(course_id).  
**Видалення:** ON DELETE CASCADE — при видаленні курсу модулі видаляються.

## 4. lessons (Уроки)
Конкретні навчальні матеріали всередині модуля.

| Стовпець | Тип | Обмеження | Опис |
|----------|-----|-----------|------|
| lesson_id | BIGSERIAL | PK | Унікальний ідентифікатор уроку. |
| title | VARCHAR(255) | NOT NULL | Назва уроку. |
| content_type | VARCHAR(50) | | Тип контенту (VIDEO, TEXT). |
| order_index | INT | | Порядковий номер уроку в модулі. |
| module_id | BIGINT | FK, NOT NULL | Посилання на modules.module_id. |

**Зв'язок:** module_id посилається на modules(module_id).  
**Видалення:** ON DELETE CASCADE — при видаленні модуля уроки видаляються.

## 5. enrollments (Зарахування)
Таблиця зв'язку (Many-to-Many) між студентами та курсами.

| Стовпець | Тип | Обмеження | Опис |
|----------|-----|-----------|------|
| enrollment_id | BIGSERIAL | PK | Унікальний ідентифікатор запису. |
| user_id | BIGINT | FK, NOT NULL | Студент (users.user_id). |
| course_id | BIGINT | FK, NOT NULL | Курс (courses.course_id). |
| enrolled_at | TIMESTAMP | DEFAULT NOW() | Дата зарахування. |

**Унікальність:** Комбінація (user_id, course_id) є унікальною (студент не може записатись на курс двічі).  
**Видалення:** ON DELETE CASCADE для обох ключів — запис зникає, якщо видалити студента або курс.

## 6. progress (Прогрес)
Відстежує статус проходження конкретного уроку студентом.

| Стовпець | Тип | Обмеження | Опис |
|----------|-----|-----------|------|
| progress_id | BIGSERIAL | PK | Унікальний ідентифікатор прогресу. |
| enrollment_id | BIGINT | FK, NOT NULL | Посилання на запис студента (enrollments). |
| lesson_id | BIGINT | FK, NOT NULL | Посилання на урок (lessons). |
| status | VARCHAR(50) | DEFAULT 'NOT_STARTED' | Статус (COMPLETED, IN_PROGRESS). |
| last_accessed | TIMESTAMP | | Час останньої активності. |