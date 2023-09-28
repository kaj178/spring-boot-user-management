package com.kaj.rest.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private List<T> data;

//    public ApiResponse(int value, List<T> userList) {
//        this.status = value;
//        this.data = userList;
//    }
}
