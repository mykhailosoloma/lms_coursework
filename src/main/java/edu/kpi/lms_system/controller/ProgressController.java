package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/complete")
    public ResponseEntity<String> completeLesson(
            @RequestParam Long userId,
            @RequestParam Long lessonId
    ) {
        progressService.completeLesson(userId, lessonId);
        return ResponseEntity.ok("Lesson completed successfully");
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> resetProgress(
            @RequestParam Long userId,
            @RequestParam Long courseId
    ) {
        progressService.resetCourseProgress(userId, courseId);
        return ResponseEntity.ok("Progress reset successfully");
    }
}