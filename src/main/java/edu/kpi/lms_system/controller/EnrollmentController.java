package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.dto.request.EnrollmentRequestDto;
import edu.kpi.lms_system.dto.response.EnrollmentResponseDto;
import edu.kpi.lms_system.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping("/latest")
    public ResponseEntity<List<Map<String, Object>>> getLatestReport() {
        return ResponseEntity.ok(enrollmentService.getLatestEnrollmentsReport());
    }
}