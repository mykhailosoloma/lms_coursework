package edu.kpi.lms_system.repository;

import edu.kpi.lms_system.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    List<User> findByFirstNameContainingIgnoreCase(String partialName);
    @Query(value = "SELECT role, COUNT(*) as count FROM users GROUP BY role", nativeQuery = true)
    List<Map<String, Object>> countUsersByRole();
}