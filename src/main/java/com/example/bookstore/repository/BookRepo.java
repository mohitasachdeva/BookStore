package com.example.bookstore.repository;

import com.example.bookstore.usermodel.BookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<BookModel,Integer> {
    @Query(value = "select * from book_model where book_Name=:bookName", nativeQuery = true)
    Optional<BookModel> getByBookName(String bookName);


    @Query(value = "update Book set quantity=:quantity where book_Id=:bookId", nativeQuery = true)
    void updateQuantity(int bookId, int quantity);

    @Query(value = "select * from book_model order by quantity asc ", nativeQuery = true)
    List<BookModel> sortedInAsc();


    @Query(value = "select * from book_model order by quantity desc", nativeQuery = true)
    List<BookModel> sortedInDesc();
}
