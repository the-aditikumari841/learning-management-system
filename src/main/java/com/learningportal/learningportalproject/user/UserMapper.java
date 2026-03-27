
package com.learningportal.learningportalproject.user;

import java.util.List;

import com.learningportal.learningportalproject.common.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, UserEntity> {
	UserDto toDto(UserEntity entity);

	UserEntity toEntity(UserDto dto);

	List<UserDto> toDto(List<UserEntity> entitylist);

	List<UserEntity> toEntity(List<UserDto> dtolist);

}