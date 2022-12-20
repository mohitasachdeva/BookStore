package com.example.bookstore.services;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.usermodel.BookModel;
import com.example.bookstore.usermodel.CartModel;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    CartModel addToCart(CartDto cartDTO);
    Optional<CartModel> getById(int id);
    List<CartModel> getAll();
    CartModel updateById(int cartId, CartDto cartDTO);


    Optional<CartModel> updateQuantity(int cartId, int quantity);

    List<CartModel> deleteById(int cartId);
}
