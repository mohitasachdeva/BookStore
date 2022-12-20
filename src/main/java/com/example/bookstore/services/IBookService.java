package com.example.bookstore.services;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.usermodel.BookModel;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    //Method to register book details
    BookModel addBook(BookDto bookDTO);

    //Method to get book by bookId
    Optional<BookModel> getByBookId(int bookId);

    //Method to get book by its bookName
    Optional<BookModel> getByBookName(String bookName);

    //Method to get all the books in database
    List<BookModel> getAll();

    //Method to update book quantity using bookID
    Optional<BookModel> updateQuantity(int bookId, int quantity);

    //Method to update book details by using bookId
    BookModel updateByBookId(int bookId, BookDto bookDTO);

    //Method to delete book details by using bookId
    List<BookModel> deleteByBookId(int bookId);

    //Method to sort book by its price in ascending order
    List<BookModel> sortedInAsc();

    //Method to sort book by its price in ascending order
    List<BookModel> sortedInDesc();
}
