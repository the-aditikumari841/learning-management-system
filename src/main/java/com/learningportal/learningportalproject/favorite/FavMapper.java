package com.learningportal.learningportalproject.favorite;

import java.util.List;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavMapper extends EntityMapper<FavDto, FavEntity> {
	FavDto toDto(FavEntity entity);

	FavEntity toEntity(FavDto dto);

	List<FavDto> toDto(List<FavEntity> entitylist);

	List<FavEntity> toEntity(List<FavDto> dtolist);
}