package edu.kpi.lms_system.model;

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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrollment_at")
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime enrollmentAt;
}
