package com.learningportal.learningportalproject.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDto> findAllUsers() {
        List<UserEntity> userEntity = userRepository.findAll();
        List<UserDto> userDtos = userMapper.toDto(userEntity);
        return userDtos;
    }

    public UserDto findById(Long userID) {
        UserEntity userEntity = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Id " + userID));
        return userMapper.toDto(userEntity);
    }

    public void deleteUser(Long userID) {

        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public UserDto saveUser(UserDto user) {
        if (user.getUserName() == null || user.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        user.setCreatedOn(Timestamp.from(Instant.now()));

        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity saved = userRepository.save(userEntity);

        return userMapper.toDto(saved);
    }

    public UserDto updateUser(UserDto userDto, Long userId) {

        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        if (userDto.getUserName() != null && userDto.getUserName().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (userDto.getPassword() != null && userDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }

        if (userDto.getUserName() != null) {
            existingUser.setUserName(userDto.getUserName());
        }
        if (userDto.getPassword() != null) {
            existingUser.setPassword(userDto.getPassword());
        }

        if (userDto.getGender() != null) {
            existingUser.setGender(userDto.getGender());
        }

        if (userDto.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(userDto.getDateOfBirth());
        }

        if (userDto.getRole() != null) {
            existingUser.setRole(userDto.getRole());
        }

        existingUser.setUpdatedOn(Timestamp.from(Instant.now()));

        UserEntity saved = userRepository.save(existingUser);

        return userMapper.toDto(saved);
    }

}
