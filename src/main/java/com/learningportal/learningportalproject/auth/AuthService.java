package com.learningportal.learningportalproject.auth;

import com.learningportal.learningportalproject.common.enums.UserRole;
import com.learningportal.learningportalproject.security.JwtService;
import com.learningportal.learningportalproject.user.UserEntity;
import com.learningportal.learningportalproject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if (userRepository.existsByUserName(request.getUserName().trim())) {
            throw new RuntimeException("Username already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(request.getUserName().trim());

        userEntity.setRole(UserRole.ROLE_LEARNER);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        userEntity.setCreatedOn(Timestamp.from(Instant.now()));

        userRepository.save(userEntity);

        return "USER REGISTERED SUCCESSFULLY";
    }

    public String login(LoginRequest request) {

        UserEntity userEntity = userRepository.findByUserName(request.getUserName().trim())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

//        return "LOGIN SUCCESSFUL";
        return jwtService.generateToken(userEntity);
    }

}
