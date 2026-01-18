package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.dto.response.LessonResponseDto;
import edu.kpi.lms_system.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/unfinished")
    public ResponseEntity<List<LessonResponseDto>> getUnfinishedLessons() {
        return ResponseEntity.ok(lessonService.findUnfinishedLessons());
    }
}