package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.dto.request.CourseRequestDto;
import edu.kpi.lms_system.dto.response.CourseResponseDto;
import edu.kpi.lms_system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.createCourseWithModules(request));
    }

    @GetMapping("/analytics/instructors")
    public ResponseEntity<List<Map<String, Object>>> getInstructorAnalytics() {
        return ResponseEntity.ok(courseService.getInstructorPerformance());
    }

    @GetMapping("/orphaned")
    public ResponseEntity<List<CourseResponseDto>> getCoursesWithoutStudents() {
        return ResponseEntity.ok(courseService.findCoursesWithoutStudents());
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getCoursesByCategory(@RequestParam String category) {
        return ResponseEntity.ok(courseService.findByCategory(category));
    }
}