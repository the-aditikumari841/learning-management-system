package com.learningportal.learningportalproject.user;

import com.learningportal.learningportalproject.common.enums.UserRole;
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
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    @GetMapping(value = "/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.userId")
    @PatchMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto updatedUser, @PathVariable Long id) {
        return userService.updateUser(updatedUser, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/role")
    public UserDto updateUserRole(@PathVariable Long id, @RequestParam UserRole role) {
        return userService.updateUserRole(id, role);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
