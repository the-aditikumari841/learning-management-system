package com.learningportal.learningportalproject.category;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Long id;
	private String name;
	private Timestamp createdOn;
	private Timestamp updatedOn;
}
