package com.example.bookstore.controller;

import com.example.bookstore.dto.*;
import com.example.bookstore.exception.USerException;
import com.example.bookstore.repository.IRepo;
import com.example.bookstore.services.IEmail;
import com.example.bookstore.services.IUserService;
import com.example.bookstore.usermodel.Email;
import com.example.bookstore.usermodel.UserModel;
import com.example.bookstore.util.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IEmail iEmail;
    @Autowired
    Token token;
    @Autowired
    IRepo iRepo;

    @PostMapping("/userAdd")
    public ResponseEntity<ResponseDto> UserRegister(@RequestBody UserDto userDto) throws USerException {
        UserModel userModel1 = iUserService.userRegister(userDto);
        String IdToken = token.createToken(userModel1.getUserID());
        Email email = new Email(userModel1.getEmail(), "Message" + IdToken, "Name" + userModel1.getFirstName());
        iEmail.sendMail(email);
        ResponseDto responseDto = new ResponseDto("New user Added", userModel1, IdToken);
        ResponseEntity<ResponseDto> response = new ResponseEntity(responseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int id) {
        UserModel userModel = iUserService.getById(id);
        ResponseDto responseDto = new ResponseDto("getting user by id", userModel, null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAll() {
        List<UserModel> userModel = iUserService.getAll();
        ResponseDto responseDto = new ResponseDto("getting all users", userModel, null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping("getbytoken/{token}")
    public ResponseEntity<ResponseDto> getById(@PathVariable String token) {
        UserModel userModel = iUserService.getByToken(token);
        ResponseDto responseDto = new ResponseDto("getting user by token", userModel, null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;

    }

//    @GetMapping("/getByEmail/{email}")
//    public ResponseEntity<ResponseDto> findByEmail(@PathVariable("email") String email) {
//        Optional<UserModel> list1 = null;
//        list1 = ((iUserService.findByEmail(email)));
//        ResponseDto responseDto = new ResponseDto("user of specific email!!!", list1, toString());
//        ResponseEntity<ResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
//        return response;
//    }

    @PostMapping("userservice/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        Optional<UserModel> userModel = iUserService.login(loginDto);
        return new ResponseEntity<ResponseDto>(new ResponseDto("login succesfully", userModel, null), HttpStatus.OK);
    }

    @PostMapping("/chngepassword/{token}")
    public UserModel chngePassword(@PathVariable String token, @RequestParam String newPassword) {
        return iUserService.chngePassword(token, newPassword);
    }
    @PostMapping("/resetpassword/{email}")
    public ResponseEntity<ResponseDto> resetPassword(@PathVariable String email,@RequestBody LoginDto loginDto,@RequestParam String token2){
        UserModel userModel=iUserService.resetPassword(email,loginDto,token2);
        ResponseDto responseDto=new ResponseDto("Password reset successfully",userModel,null);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
