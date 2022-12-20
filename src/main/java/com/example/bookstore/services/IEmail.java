package com.example.bookstore.services;

import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.usermodel.Email;
import org.springframework.http.ResponseEntity;

public interface IEmail {
    ResponseEntity<ResponseDto> sendMail(Email email);


}
