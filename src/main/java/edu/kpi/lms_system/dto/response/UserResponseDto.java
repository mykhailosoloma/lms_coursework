package edu.kpi.lms_system.dto.response;

import edu.kpi.lms_system.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class UserResponseDto {

    private Long id;
    private  String email;
    private  String firstName;
    private  String lastName;
    private User.Role role;
    private LocalDateTime createdAt;

}
