package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {



        private int quantity;

        private int bookId;

        private int userID;
}
