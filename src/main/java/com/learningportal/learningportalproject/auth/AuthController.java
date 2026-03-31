package com.learningportal.learningportalproject.auth;

import com.learningportal.learningportalproject.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest request) {

        String result = authService.register(request);

        ApiResponse response = new ApiResponse(result, "SUCCESS", LocalDateTime.now(), null);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {

        String result = authService.login(request);

        ApiResponse response = new ApiResponse(result, "SUCCESS", LocalDateTime.now(), null);

        return ResponseEntity.ok(response);
    }
}
