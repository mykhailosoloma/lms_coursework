package edu.kpi.lms_system.service;

import edu.kpi.lms_system.dto.mapping.UserMapper;
import edu.kpi.lms_system.dto.request.UserRequestDto;
import edu.kpi.lms_system.dto.response.UserResponseDto;
import edu.kpi.lms_system.model.User;
import edu.kpi.lms_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Transactional
    public UserResponseDto registerUser(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already taken");
        }
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
    @Transactional
    public void deleteUserHard(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public List<Map<String, Object>> getUserRoleStatistics() {
        return userRepository.countUsersByRole();
    }

    public List<UserResponseDto> searchUsersByName(String namePart) {
        return userRepository.findByFirstNameContainingIgnoreCase(namePart).stream()
                .map(userMapper::toDto)
                .toList();
    }
}