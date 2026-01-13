package edu.kpi.lms_system.model;


import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "enrollments", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"user_id", "course_id"})}
      )
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson_id;

    @Column(name = "enrollment_at")
    private LocalDateTime enrollment_at = LocalDateTime.now();
}
