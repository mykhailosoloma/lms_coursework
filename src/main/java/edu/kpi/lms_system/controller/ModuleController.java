package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.dto.request.ModuleRequestDto;
import edu.kpi.lms_system.dto.response.ModuleResponseDto;
import edu.kpi.lms_system.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponseDto> addModule(@RequestBody ModuleRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(moduleService.addModuleToCourse(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleResponseDto> updateModule(
            @PathVariable Long id,
            @RequestParam String newTitle
    ) {
        return ResponseEntity.ok(moduleService.updateModule(id, newTitle));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ModuleResponseDto>> findModulesByCourseName(@RequestParam String courseTitle) {
        return ResponseEntity.ok(moduleService.findModulesByCourseTitle(courseTitle));
    }
}