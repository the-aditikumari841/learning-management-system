package com.learningportal.learningportalproject.user;

import com.learningportal.learningportalproject.common.enums.UserRole;
import com.learningportal.learningportalproject.user.dto.CreateUserRequest;
import com.learningportal.learningportalproject.user.dto.UpdateUserRequest;
import com.learningportal.learningportalproject.user.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me")
    public UserResponse getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponse saveUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.saveUser(request);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest request, @PathVariable Long id) {
        return userService.updateUser(request, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/role")
    public UserResponse updateUserRole(@PathVariable Long id, @RequestParam UserRole role) {
        return userService.updateUserRole(id, role);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
