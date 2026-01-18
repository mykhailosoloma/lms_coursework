package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findAllByCourseIdOrderByOrderIndexAsc(Long courseId);
}