package com.example.bookstore.repository;

import com.example.bookstore.usermodel.OrderModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<OrderModel,Integer> {


}
