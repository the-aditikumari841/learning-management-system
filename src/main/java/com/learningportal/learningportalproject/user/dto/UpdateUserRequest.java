package com.learningportal.learningportalproject.user.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UpdateUserRequest {
    private String userName;
    private String password;
    private Date dateOfBirth;
    private String gender;
}
