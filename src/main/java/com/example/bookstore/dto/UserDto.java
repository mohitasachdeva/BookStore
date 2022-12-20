package com.example.bookstore.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UserDto {
    @NotEmpty(message = "name cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "user name Invalid")
    public  String  firstName;
    @NotEmpty(message = "Lname cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "user name Invalid")
    public String  lastName;
    public String  email;
    public String  address;
    public String  DOB;
    public String  password;
    public int loginId;

    public UserDto( String firstName, String lastName, String email, String address, String DOB, String password,int loginId) {

         this.firstName  = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.DOB = DOB;
        this.password = password;
        this.loginId=loginId;
    }
}
