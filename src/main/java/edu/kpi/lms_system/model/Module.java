package edu.kpi.lms_system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "modules")

public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @ToString.Exclude
    private Course course;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Lesson> lessons;


}
