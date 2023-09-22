package com.kaj.rest.api.controller;

import com.kaj.rest.api.model.User;
import com.kaj.rest.api.service.ExcelExporter;
import com.kaj.rest.api.service.UserApiServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {
    @Autowired
    private UserApiServiceImpl apiService;
    @Autowired
    private ExcelExporter exporter;

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

    @GetMapping("/exporter/excel")
    public void excelExport(HttpServletResponse response) throws IOException {
        exporter.writeExcel(response);
    }
}
