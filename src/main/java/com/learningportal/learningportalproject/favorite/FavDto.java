package com.learningportal.learningportalproject.favorite;

import java.sql.Timestamp;

import com.learningportal.learningportalproject.course.CourseEntity;
import com.learningportal.learningportalproject.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavDto {
	private long favouriteId;
	private UserEntity learnerId;
	private CourseEntity courseId;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}