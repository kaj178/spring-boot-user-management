package com.kaj.rest.service;

import com.kaj.rest.model.User;
import org.springframework.http.ResponseEntity;

public interface UserApiService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> getUserById(Integer id);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(Integer id, User user);

    ResponseEntity<?> deleteUser(Integer id);
}
