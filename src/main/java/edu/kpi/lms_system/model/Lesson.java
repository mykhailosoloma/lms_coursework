package edu.kpi.lms_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lessons")

public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private ContentType contentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    public enum ContentType{
        VIDEO,
        AUDIO,
        TEXT,
    }
}
