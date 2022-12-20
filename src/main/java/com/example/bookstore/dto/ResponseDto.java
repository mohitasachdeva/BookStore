package com.example.bookstore.dto;

import lombok.Data;


@Data
public class ResponseDto {

    private String message;
    private Object data;
    private String token;


    public ResponseDto(String message, Object data, String token) {
        this.token = token;
        this.message = message;
        this.data = data;


    }

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;

    }
}
