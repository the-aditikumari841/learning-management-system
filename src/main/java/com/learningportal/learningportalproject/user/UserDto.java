package com.learningportal.learningportalproject.user;

import com.learningportal.learningportalproject.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String userName;
    private String password;
    private String gender;
    private Date dateOfBirth;
    private UserRole role;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
