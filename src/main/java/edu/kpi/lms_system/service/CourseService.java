package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.CourseMapper;
import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.model.Course;
import edu.kpi.lms_system.model.Module;
import edu.kpi.lms_system.model.User;
import edu.kpi.lms_system.repository.CourseRepository;
import edu.kpi.lms_system.repository.ModuleRepository;
import edu.kpi.lms_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public CourseResponseDto createCourseWithModules(CourseRequestDto request) {
        User instructor = userRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        Course course = courseMapper.toEntity(request);
        course.setInstructor(instructor);
        Course savedCourse = courseRepository.save(course);
        if (request.getModulesTitles() != null && !request.getModulesTitles().isEmpty()) {
            List<Module> modules = new ArrayList<>();
            int order = 1;
            for (String title : request.getModulesTitles()) {
                if (title == null || title.trim().isEmpty()) {
                    throw new RuntimeException("Module title cannot be empty");
                }
                Module module = new Module();
                module.setTitle(title);
                module.setOrderIndex(order++);
                module.setCourse(savedCourse);
                modules.add(module);
            }
            moduleRepository.saveAll(modules);
        }
        return courseMapper.toDto(savedCourse);
    }

    public List<Map<String, Object>> getInstructorPerformance() {
        return courseRepository.getInstructorPerformanceAnalytics();
    }

    public List<CourseResponseDto> findCoursesWithoutStudents() {
        return courseRepository.findCoursesWithoutStudents().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    public List<CourseResponseDto> findByCategory(String category) {
        return courseRepository.findAllByCategory(category).stream()
                .map(courseMapper::toDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getFullContentReport() {
        return courseRepository.getFullContentReport();
    }
}