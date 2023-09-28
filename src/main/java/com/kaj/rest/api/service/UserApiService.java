package com.kaj.rest.api.service;

import com.kaj.rest.api.model.entities.User;
import org.springframework.http.ResponseEntity;

public interface UserApiService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> getUsersPaginated(int page);

    ResponseEntity<?> getUserById(String id);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(String id, User user);

    ResponseEntity<?> deleteUser(String id);
}
