package com.kaj.rest.api.service.impl;

import com.kaj.rest.api.exception.UserExistedException;
import com.kaj.rest.api.exception.UserNotFoundException;
import com.kaj.rest.api.model.responses.ApiResponse;
import com.kaj.rest.api.model.entities.User;
import com.kaj.rest.api.repository.UserRepository;
import com.kaj.rest.api.service.UserApiService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiServiceImpl implements UserApiService {
    private UserRepository userRepository;

    public UserApiServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Http response status: 200
    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userRepository.findAll();
        ApiResponse<User> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Read users successfully!", userList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    // Http response status: 200
    public ResponseEntity<?> getUsersPaginated(int page) {
        // PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("fieldName"));
        Pageable paging = PageRequest.of(page, 5, Sort.by("user_id").ascending());
        Page<User> userList = userRepository.findAll(paging);
        // Convert Page instance to List instance
        ApiResponse<User> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Export successfully!", userList.stream().toList());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            // Http response status: 409
            throw new UserNotFoundException("User not found!");
        }
        // Http response status: 201
        // Change optional object to list object
        List<User> userList =  userOptional.stream().toList();
        ApiResponse<User> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Read user successfully!", userList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        List<Boolean> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(user.getUserID());
        if (userOptional.isPresent()) {
            throw new UserExistedException("User existed!");
        } else {
            // Http response status: 200
            userRepository.save(user);
        }
        responseList.add(true);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), "Create user successfully!", responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateUser(String id, User newUser) {
        List<Boolean> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(id);

        // Kiểm tra xem entity có trong database hay không
        // Sau đó lấy entity đó ra và cập nhật các thuộc tính của object được gửi đến
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        } else {
            User user = userOptional.get();
            user.setUserID(newUser.getUserID());
            user.setUserLastName(newUser.getUserLastName());
            user.setUserFirstName(newUser.getUserFirstName());
            user.setUserBirthDay(newUser.getUserBirthDay());
            user.setUserEmail(newUser.getUserEmail());
            user.setUserPhone(newUser.getUserPhone());
            user.setUserGender(newUser.getUserGender());
            userRepository.save(user);
        }
        responseList.add(true);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Update user successfully!", responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        List<Boolean> responseList = new LinkedList<>();
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found!");
        } else {
            userRepository.deleteById(id);
        }
        responseList.add(true);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "Delete user successfully!", responseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
