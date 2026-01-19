package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findAllByCourseIdOrderByOrderIndexAsc(Long courseId);

    @Query(value = """
        SELECT m.* FROM modules m
        JOIN courses c ON m.course_id = c.course_id
        WHERE c.title = :courseTitle
    """, nativeQuery = true)
    List<Module> findModulesByCourseTitleNative(@Param("courseTitle") String courseTitle);
}