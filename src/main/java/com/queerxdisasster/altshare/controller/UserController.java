package com.queerxdisasster.altshare.controller;

import com.queerxdisasster.altshare.model.User;
import com.queerxdisasster.altshare.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.badRequest().build();
        }
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername);
        return ResponseEntity.ok(currentUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            user.setId(existingUser.getId());
            userService.update(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User existingUser = userService.findById(id);
        if (existingUser != null) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
