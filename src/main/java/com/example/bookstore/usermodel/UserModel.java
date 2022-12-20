package com.example.bookstore.usermodel;

import com.example.bookstore.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserModel {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int   UserID;
  private  String  FirstName;
  private String  LastName;
   private String  email;
  private String  address;
  private String  DOB;
  private String  password;
  private  int  loginId;

    public UserModel(UserDto userDto) { //saving

      this.FirstName =userDto.firstName;
      this.LastName = userDto.lastName;
      this.email = userDto.email;
        this.address = userDto.address;
        this.DOB = userDto.DOB;
        this.password = userDto.password;
        this.loginId=userDto.loginId;
    }


    public UserModel() {
    }
  public UserModel(UserDto userDto , int UserID) { //updating
       this.UserID=UserID;
    this.FirstName =userDto.firstName;
    this.LastName = userDto.lastName;
    this.email = userDto.email;
    this.address = userDto.address;
    this.DOB = userDto.DOB;
    this.password = userDto.password;
    this.loginId =userDto.loginId;
  }


}
