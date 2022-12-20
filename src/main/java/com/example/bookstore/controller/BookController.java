package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.services.IBookService;
import com.example.bookstore.usermodel.BookModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    IBookService bookIService;

    //Method to insert book details
    @PostMapping("/addbook")
    public ResponseEntity<ResponseDto> addBook( @RequestBody BookDto bookDTO) {
        bookIService.addBook(bookDTO);
        ResponseDto responseDTO = new ResponseDto("Book added Successfully", bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Method to get book by bookId
    @GetMapping("/getbyId/{bookId}")
    public ResponseEntity<ResponseDto> getByBookId(@PathVariable int bookId) {
        Optional<BookModel> findBook = bookIService.getByBookId(bookId);
        ResponseDto responseDTO = new ResponseDto("Get by BookId successfully", findBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }
    //Method to get book by bookName
    @GetMapping("/getbyBookName")
    public ResponseEntity<ResponseDto> getByBookName(@RequestParam String bookName) {
        Optional<BookModel>findbookbyname= bookIService.getByBookName(bookName);
        ResponseDto responseDTO = new ResponseDto("Get by  book name successfully", findbookbyname);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }

    //Method to get all the books in database
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> getAll() {
        List<BookModel> bookList = bookIService.getAll();
        ResponseDto responseDTO = new ResponseDto("Get all books successfully", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //Method to update the quantity of books
    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int bookId, @RequestParam int quantity) {
        Optional<BookModel> updatedQuantity = bookIService.updateQuantity(bookId, quantity);
        ResponseDto responseDTO = new ResponseDto("update quantity successfully", updatedQuantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Method to update book details by using bookId
    @PutMapping("/updateById")
    public ResponseEntity<ResponseDto> updateByBookId(@Valid @RequestParam int bookId, @RequestBody BookDto bookDTO) {
        BookModel bookModel = bookIService.updateByBookId(bookId, bookDTO);
        ResponseDto responseDTO = new ResponseDto("Updated by  bookid successfully", bookModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to delete book details by using bookId
    @DeleteMapping("/del/{bookId}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int bookId) {
        List<BookModel> bookModels = bookIService.deleteByBookId(bookId);
        ResponseDto responseDTO = new ResponseDto("book deleted successfully", bookModels);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to sort books in ascending order by their price
    @GetMapping("/sortingA")
    public ResponseEntity<ResponseDto> sortInAsc() {
        List<BookModel> bookModelList = bookIService.sortedInAsc();
        ResponseDto responseDTO = new ResponseDto("Sorted in ascending order successfully", bookModelList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to sort books in descending order by their price
    @GetMapping("/sortingD")
    public ResponseEntity<ResponseDto> sortInDesc() {
        List<BookModel> sortedList = bookIService.sortedInDesc();
        ResponseDto responseDTO = new ResponseDto("Sorted in descending order successfully", sortedList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
