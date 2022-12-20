package com.example.bookstore.repository;


import com.example.bookstore.usermodel.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepo  extends JpaRepository<CartModel,Integer> {
    @Query(value = "update cart_model set quantity=:quantity where cart_id=:cartId", nativeQuery = true)
    void updateQuantity(int cartId, int quantity);
}
