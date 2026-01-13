package edu.kpi.lms_system.model;

import jakarta.persistence.*;
import jdk.jfr.ContentType;
import lombok.Data;

@Data
@Entity
@Table(name = "lessons")

public class Lesson {

    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private ContentType contentType;

    public enum ContentType{
        VIDEO,
        AUDIO,
        TEXT,
    }
}
