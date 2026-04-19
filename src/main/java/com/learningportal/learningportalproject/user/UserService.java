package com.learningportal.learningportalproject.user;

import com.learningportal.learningportalproject.common.enums.UserRole;
import com.learningportal.learningportalproject.user.dto.CreateUserRequest;
import com.learningportal.learningportalproject.user.dto.UpdateUserRequest;
import com.learningportal.learningportalproject.user.dto.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public List<UserResponse> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userMapper.toResponse(users);
    }

    public UserResponse findById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Id " + userId));
        return userMapper.toResponse(userEntity);
    }

    public UserResponse getCurrentUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserEntity currentUser)) {
            throw new IllegalStateException("Invalid authentication principal");
        }

        return userMapper.toResponse(currentUser);
    }

    public void deleteUser(Long userId) {

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Id " + userId));

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserEntity currentUser)) {
            throw new IllegalStateException("Invalid authentication principal");
        }

        if (currentUser.getUserId() == userId) {
            throw new IllegalStateException("Admins cannot delete themselves");
        }

        if (userEntity.getRole() == UserRole.ROLE_ADMIN) {
            long adminCount = userRepository.countByRole(UserRole.ROLE_ADMIN);

            if (adminCount <= 1) {
                throw new IllegalStateException("Cannot delete the last admin");
            }
        }
        userRepository.delete(userEntity);
    }

    public UserResponse saveUser(CreateUserRequest request) {

        //System.out.println("RAW : " + userDto.getPassword());
        //System.out.println("ENCODED : " + passwordEncoder.encode(userDto.getPassword()));

        String userName = request.getUserName().trim();

        if (userRepository.existsByUserName(userName)) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setUserName(userName);

        userEntity.setCreatedOn(Timestamp.from(Instant.now()));

        userEntity.setPassword(passwordEncoder.encode(request.getPassword().trim()));

        UserEntity saved = userRepository.save(userEntity);

        return userMapper.toResponse(saved);
    }

    public UserResponse updateUserRole(Long userId, UserRole role) {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (!(principal instanceof UserEntity currentUser)) {
            throw new IllegalStateException("Invalid authentication principal");
        }

        if (currentUser.getUserId() == userId) {
            throw new IllegalArgumentException("Admins cannot change their own role");
        }

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with Id" + userId));

        userEntity.setRole(role);
        return userMapper.toResponse(userRepository.save(userEntity));
    }

    public UserResponse updateUser(UpdateUserRequest request, Long userId) {

        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        if (request.getUserName() != null) {
            String newUserName = request.getUserName().trim();

            if (!newUserName.equals(existingUser.getUserName()) &&
                    userRepository.existsByUserName(newUserName)) {
                throw new RuntimeException("Username already exists");
            }

            existingUser.setUserName(newUserName);
        }
        if (request.getPassword() != null && request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }

        if (request.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        }

        if (request.getGender() != null) {
            existingUser.setGender(request.getGender());
        }

        if (request.getDateOfBirth() != null) {
            existingUser.setDateOfBirth(request.getDateOfBirth());
        }

        existingUser.setUpdatedOn(Timestamp.from(Instant.now()));

        UserEntity saved = userRepository.save(existingUser);

        return userMapper.toResponse(saved);
    }

}
