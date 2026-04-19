package com.learningportal.learningportalproject.category;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, CourseCategoryEntity> {
    CategoryDto toDto(CourseCategoryEntity entity);

    CourseCategoryEntity toEntity(CategoryDto dto);

    List<CategoryDto> toDto(List<CourseCategoryEntity> entitylist);

    List<CourseCategoryEntity> toEntity(List<CategoryDto> dtolist);

}
