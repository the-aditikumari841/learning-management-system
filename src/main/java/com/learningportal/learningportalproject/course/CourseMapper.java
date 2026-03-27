package com.learningportal.learningportalproject.course;

import java.util.List;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDto, CourseEntity> {
	CourseDto toDto(CourseEntity entity);

	CourseEntity toEntity(CourseDto dto);

	List<CourseDto> toDto(List<CourseEntity> entitylist);

	List<CourseEntity> toEntity(List<CourseDto> dtolist);

}