package com.learningportal.learningportalproject.favorite;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavMapper extends EntityMapper<FavDto, FavEntity> {
    FavDto toDto(FavEntity entity);

    FavEntity toEntity(FavDto dto);

    List<FavDto> toDto(List<FavEntity> entitylist);

    List<FavEntity> toEntity(List<FavDto> dtolist);
}