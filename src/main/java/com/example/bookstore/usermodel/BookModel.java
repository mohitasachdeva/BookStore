package com.example.bookstore.usermodel;

import com.example.bookstore.dto.BookDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookModel {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)

        private int bookId;
        private String bookName;
        private String authorName;
        private String bookDescription;
        private String bookImg;
        private int price;
        private int quantity;

        //Constructor to insert the data
        public BookModel(BookDto bookDTO) {
            this.bookName = bookDTO.getBookName();
            this.authorName = bookDTO.getAuthorName();
            this.bookDescription = bookDTO.getBookDescription();
            this.bookImg = bookDTO.getBookImg();
            this.price = bookDTO.getPrice();
            this.quantity = bookDTO.getQuantity();
        }

        //Constructor to update data using id
        public BookModel(int bookID, BookDto bookDTO) {
            this.bookId = bookID;
            this.bookName = bookDTO.getBookName();
            this.authorName = bookDTO.getAuthorName();
            this.bookDescription = bookDTO.getBookDescription();
            this.bookImg = bookDTO.getBookImg();
            this.price = bookDTO.getPrice();
            this.quantity = bookDTO.getQuantity();
        }

    public BookModel() {

    }
}
