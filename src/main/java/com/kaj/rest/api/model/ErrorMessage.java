package com.kaj.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String message;

//    public ErrorMessage(int value, String message) {
//        this.statusCode = value;
//        this.message = message;
//    }
}
