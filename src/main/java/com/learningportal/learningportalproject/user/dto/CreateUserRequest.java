package com.learningportal.learningportalproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.sql.Date;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Password is required")
    private String password;

    private String gender;
    private Date dateOfBirth;

}
