package com.example.bookstore.services;

import com.example.bookstore.dto.LoginDto;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.USerException;
import com.example.bookstore.repository.IRepo;
import com.example.bookstore.usermodel.Email;
import com.example.bookstore.usermodel.UserModel;
import com.example.bookstore.util.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    IRepo iRepo;
    @Autowired
    Token token;
    @Autowired
    IEmail iEmail;

    @Override
    public UserModel userRegister(UserDto userDto) {
        UserModel userModel = new UserModel(userDto);
        return iRepo.save(userModel);
    }

    public UserModel getById(int id) {
        Optional<UserModel> userModel = iRepo.findById(id);
        if (userModel.isPresent()) {
            return userModel.get();
        } else {
            throw new USerException("id not found");
        }
    }

    public List<UserModel> getAll() {
        List<UserModel> userModelList = iRepo.findAll();
        return userModelList;
    }

    @Override
    public UserModel getByToken(String token1) {
        String find = String.valueOf(token.decodeToken(token1));
        return iRepo.findById(Integer.valueOf(find)).get();
    }

    @Override
    public Optional<UserModel> login(LoginDto loginDto) {
      Optional <UserModel> userModel1 = iRepo.findByEmail(loginDto.getEmail());
        log.info("data in usermodel1");
        if (userModel1.isPresent()) {
            String passwordInDatabase = userModel1.get().getPassword();
            String passwordEntered = loginDto.getPassword();
            if (passwordEntered.equals(passwordInDatabase)) {
                log.info("login successfull");
            } else {
                throw new USerException("id not found");
            }
        } else {
            throw new USerException("user not found");
        }
        return userModel1;
    }

    @Override
    public UserModel chngePassword(String token1, String newPassword) {
        int token2 = token.decodeToken(token1);
        Optional<UserModel> userModel = iRepo.findById(token2);
        userModel.get().setPassword(newPassword);
        iRepo.save(userModel.get());
        return userModel.get();
    }

    //    public Optional<UserModel> findByEmail(String email)
//    {
//        return iRepo.findByEmail(email);
//    }

    public UserModel resetPassword(String email, LoginDto loginDto, String token1) {
        int tokenId = token.decodeToken(token1);
        Optional<UserModel> optionalUserModel = iRepo.findById(tokenId);
        Optional<UserModel> userModelOptional = iRepo.getByEmailId(email);
        if (userModelOptional.isPresent() && optionalUserModel.isPresent()) {
            UserModel userModel = iRepo.getByEmailId(email).get();
            userModel.setPassword(loginDto.getPassword());
            Email email1 = new Email(email, "password reset successfully", "NewPassword is :" + loginDto.getPassword());
            iEmail.sendMail(email1);
            iRepo.save(userModel);
            return userModel;
        } else {
            throw new USerException("id not found");
        }
    }

}











