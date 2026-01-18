package edu.kpi.lms_system.controller;

import edu.kpi.lms_system.dto.request.UserRequestDto;
import edu.kpi.lms_system.dto.response.UserResponseDto;
import edu.kpi.lms_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.registerUser(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserHard(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/analytics/roles")
    public ResponseEntity<List<Map<String, Object>>> getRoleStats() {
        return ResponseEntity.ok(userService.getUserRoleStatistics());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam String name) {
        return ResponseEntity.ok(userService.searchUsersByName(name));
    }
}