package com.example.bookstore.services;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.exception.USerException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.usermodel.BookModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements  IBookService {
    @Autowired
    BookRepo bookStoreRepository;

    //Method to register book details

    public BookModel addBook(BookDto bookDTO) {
        BookModel addBook= new BookModel(bookDTO);
        bookStoreRepository.save(addBook);
        return addBook;
    }

    //Method to get book by bookId

    public Optional<BookModel> getByBookId(int bookId) {
        Optional<BookModel> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new USerException("this bookId is not Present");
        }
    }

    //Method to get book by its bookName

    public Optional<BookModel> getByBookName(String bookName) {
        Optional<BookModel> book = bookStoreRepository.getByBookName(bookName);
        if (book.isPresent()) {
            return book;
        } else {
            throw new USerException("Book of this name is not present");
        }
    }

    //Method to get all the books in database

    public List<BookModel> getAll() {
        List<BookModel> bookModelList = bookStoreRepository.findAll();
        return bookModelList;
    }

    //Method to update book quantity using bookID

    public Optional<BookModel> updateQuantity(int bookId, int quantity) {
        if (bookStoreRepository.existsById(bookId)) {
            bookStoreRepository.updateQuantity(bookId, quantity);
            return getByBookId(bookId);
        } else {
            throw new USerException("Book not present of this Id");
        }
    }
    //Method to update book details by using bookId

    public BookModel updateByBookId(int bookId, BookDto bookDTO) {
        Optional<BookModel> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            BookModel updatedBook = new BookModel(bookId, bookDTO);
            bookStoreRepository.save(updatedBook);
            return updatedBook;
        } else throw new USerException("Book not present of this Id");
    }

    //Method to delete book details by using bookId

    public List<BookModel> deleteByBookId(int bookId) {
        Optional<BookModel> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            bookStoreRepository.deleteById(bookId);
            return bookStoreRepository.findAll();
        } else throw new USerException("Book not present of this Id");
    }

    //Method to sort book by its price in ascending order

    public List<BookModel> sortedInAsc() {
        List<BookModel> sortedList = bookStoreRepository.sortedInAsc();
        if (sortedList.isEmpty()) {
            throw new USerException("Book not present for sorting");
        } else return sortedList;
    }

    //Method to sort book by its price in ascending order

    public List<BookModel> sortedInDesc() {
        List<BookModel> sortedList = bookStoreRepository.sortedInDesc();
        if (sortedList.isEmpty()) {
            throw new USerException("Book not present for sorting");
        } else return sortedList;


    }
}
