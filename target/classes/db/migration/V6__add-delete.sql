ALTER TABLE courses DROP CONSTRAINT fk_course_instructor;
ALTER TABLE courses
    ADD CONSTRAINT fk_course_instructor
        FOREIGN KEY (instructor_id) REFERENCES users(user_id)
            ON DELETE CASCADE;