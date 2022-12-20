package com.example.bookstore.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class BookDto {
        @NotEmpty(message = "Book Name is mandatory")
        @Pattern(regexp = "^[A-Z]{1}.{2,}$", message = "Book Name Invalid")
        private String bookName;

        @NotEmpty(message = "Author Name is mandatory")
        @Pattern(regexp = "^[A-Z]{1}.{2,}$", message = "Author Name Invalid")
        private String authorName;

        @Pattern(regexp = ".{2,5}", message = "Book description should be in limit 2 to 5 words")
        private String bookDescription;

        private String bookImg;

        @Max(value = 10000, message = "Maximum value for the book is 10000")
        private int price;

        @Max(10) @Min(1)
        private int quantity;

        public BookDto(String bookName, String authorName, String bookDescription, String bookImg, int price, int quantity) {
                this.bookName = bookName;
                this.authorName = authorName;
                this.bookDescription = bookDescription;
                this.bookImg = bookImg;
                this.price = price;
                this.quantity = quantity;
        }
}

