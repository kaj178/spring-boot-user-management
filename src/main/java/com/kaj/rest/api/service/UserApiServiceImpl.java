package com.kaj.rest.api.service;

import com.kaj.rest.api.exception.UserExistedException;
import com.kaj.rest.api.exception.UserNotFoundException;
import com.kaj.rest.api.model.ApiResponse;
import com.kaj.rest.api.model.User;
import com.kaj.rest.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiServiceImpl implements UserApiService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> getAllUsers() {
        // Http response status: 200
        List<User> userList = userRepository.findAll();
        ApiResponse<User> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), userList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            // Http response status: 409
            throw new UserNotFoundException("User not found!");
        }
        // Http response status: 201
        // Change optional object to list object
        List<User> userList =  userOptional.stream().toList();
        ApiResponse<User> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), userList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        List<String> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            throw new UserExistedException("User existed!");
        }
        // Http response status: 200
        userRepository.save(user);
        responseList.add("Add user successfully!");
        ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateUser(Integer id, User newUser) {
        List<String> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(id);
        // Kiểm tra xem entity có trong database hay không
        // Sau đó lấy entity đó ra và cập nhật các thuộc tính của object được gửi đến
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }
        User user = userOptional.get();
        user.setName(newUser.getName());
        user.setGender(newUser.getGender());
        user.setStatus(newUser.getStatus());
        userRepository.save(user);

        responseList.add("Update user successfully!");
        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(Integer id) {
        List<String> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        }
        userRepository.deleteById(id);

        responseList.add("Delete user successfully!");
        ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
