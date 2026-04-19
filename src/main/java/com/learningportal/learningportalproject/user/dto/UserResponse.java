package com.learningportal.learningportalproject.user.dto;

import com.learningportal.learningportalproject.common.enums.UserRole;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class UserResponse {
    private long userId;
    private String userName;
    private String gender;
    private Date dateOfBirth;
    private UserRole role;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
