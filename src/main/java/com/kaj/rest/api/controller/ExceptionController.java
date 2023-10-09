package com.kaj.rest.api.controller;

import com.kaj.rest.api.exception.UserExistedException;
import com.kaj.rest.api.exception.UserNotFoundException;
import com.kaj.rest.api.model.responses.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<?> handleUserExistedException(UserExistedException ex, WebRequest request) {
        ErrorMessage errorResponse = new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
