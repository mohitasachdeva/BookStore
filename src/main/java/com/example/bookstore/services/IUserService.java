package com.example.bookstore.services;

import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.usermodel.UserModel;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserModel userRegister(UserDto userDto);
    UserModel getById(int UserID);
    List<UserModel> getAll();
    UserModel getByToken( String token1);

    Optional<UserModel> login(LoginDto loginDto);

//    Optional<UserModel> findByEmail(String email);


    UserModel chngePassword(String token, String newPassword);
//     String  forgotPassword(String userModel);

     UserModel resetPassword(String email,LoginDto loginDto,String token1);
}
