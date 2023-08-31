package com.kaj.rest.api.controller;

import com.kaj.rest.api.model.User;
import com.kaj.rest.api.service.UserApiServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {
    @Autowired
    private UserApiServiceImplement apiService;

    @GetMapping
    public ResponseEntity<?> readUsers() {
        return apiService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readUserById(@PathVariable Integer id) {
        return apiService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return apiService.addUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Integer id,
            @RequestBody User user
    ) {
        return apiService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return apiService.deleteUser(id);
    }
}
