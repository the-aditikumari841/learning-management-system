package com.learningportal.learningportalproject.course;

import com.learningportal.learningportalproject.category.CourseCategoryEntity;
import com.learningportal.learningportalproject.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private long courseId;
    private String title;
    private String description;
    private UserEntity userId;
    private CourseCategoryEntity category;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}
