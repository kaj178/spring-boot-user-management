package com.kaj.rest.api.controller;

import com.kaj.rest.api.model.entities.User;
import com.kaj.rest.api.service.ExcelExpoter;
import com.kaj.rest.api.service.UserApiService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private UserApiService apiService;
    private ExcelExpoter exporter;

    public UserApiController(UserApiService service, ExcelExpoter exporter) {
        this.apiService = service;
        this.exporter = exporter;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return apiService.getAllUsers();
    }

    @GetMapping(params = "page")
    public ResponseEntity<?> readUsers(@RequestParam("page") int page) {
        return apiService.getUsersPaginated(page - 1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readUserById(@PathVariable String id) {
        return apiService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return apiService.addUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable String id,
            @RequestBody User user
    ) {
        return apiService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return apiService.deleteUser(id);
    }

    @GetMapping("/exporter/excel")
    public void excelExport(HttpServletResponse response) throws IOException {
        exporter.writeExcel(response);
    }
}
