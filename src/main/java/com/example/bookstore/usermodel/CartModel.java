package com.example.bookstore.usermodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Entity
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int cartId;
    @ManyToOne
    @JoinColumn(name = "cart_book_id")
    private BookModel book;

    @OneToOne
    @JoinColumn(name = "cart_user_id")
    private UserModel user;

    private int quantity;


    //Constructor to add data
    public CartModel(BookModel book, UserModel user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    //Constructor to update data
    public CartModel(int cartId, BookModel book, UserModel user, int quantity) {
        this.cartId = cartId;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public CartModel() {

    }
}
