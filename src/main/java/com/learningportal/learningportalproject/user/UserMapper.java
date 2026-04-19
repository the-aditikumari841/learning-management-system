package com.learningportal.learningportalproject.user;

import com.learningportal.learningportalproject.user.dto.CreateUserRequest;
import com.learningportal.learningportalproject.user.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(UserEntity entity);

    UserEntity toEntity(CreateUserRequest request);

    List<UserResponse> toResponse(List<UserEntity> entities);

}
