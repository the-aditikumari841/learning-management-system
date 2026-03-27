package com.learningportal.learningportalproject.category;

import java.util.List;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, CourseCategoryEntity> {
	CategoryDto toDto(CourseCategoryEntity entity);

	CourseCategoryEntity toEntity(CategoryDto dto);

	List<CategoryDto> toDto(List<CourseCategoryEntity> entitylist);

	List<CourseCategoryEntity> toEntity(List<CategoryDto> dtolist);

}