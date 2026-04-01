package com.learningportal.learningportalproject.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private String status;
    private String token;
    private LocalDateTime timestamp;

    private Map<String, String> errors;
}
