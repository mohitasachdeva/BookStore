package com.example.bookstore.repository;

import com.example.bookstore.usermodel.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepo extends JpaRepository<UserModel,Integer> {
    @Query(value = "select * from user_model where email= :email ", nativeQuery = true)
   Optional <UserModel> findByEmail(String email);

//    Optional<UserModel> findByEmailAndPassword(String email, String password);
@Query(value = "select * from user_model where email= :email ", nativeQuery = true)
Optional<UserModel>getByEmailId(String email);
}
