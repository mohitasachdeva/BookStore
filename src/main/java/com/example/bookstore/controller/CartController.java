package com.example.bookstore.controller;


import com.example.bookstore.dto.CartDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.services.ICartService;
import com.example.bookstore.usermodel.CartModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
    public class CartController {
        @Autowired
        ICartService cartIService;

        //Method to insert book details
        @PostMapping("/addToCart")
        public ResponseEntity<ResponseDto> addToCart( @RequestBody CartDto cartDTO) {
            CartModel cart = cartIService.addToCart(cartDTO);
            ResponseDto responseDTO = new ResponseDto("Details added Successfully", cart);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        //Method to get book by bookId
        @GetMapping("/getbyId/{cartId}")
        public ResponseEntity<ResponseDto> getByCartId(@PathVariable int cartId, @RequestParam String token) {
            Optional<CartModel> foundedItem = cartIService.getById(cartId);
            ResponseDto responseDTO = new ResponseDto("Get call userId successfully", foundedItem);
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }


        //Method to get all the books in database
        @GetMapping("/getall")
        public ResponseEntity<ResponseDto> getAll() {
            List<CartModel> cartList = cartIService.getAll();
            ResponseDto responseDTO = new ResponseDto("Get all Id successfully", cartList);
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }


        //Method to update list using body
        @PutMapping("/updateById/{cartId}")
        public ResponseEntity<ResponseDto> updateById( @PathVariable int cartId, @RequestBody CartDto cartDTO) {
            CartModel cartModel = cartIService.updateById(cartId, cartDTO);
            ResponseDto responseDTO = new ResponseDto("update successfully", cartModel);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

        //Method to update quantity of cart by using cartId
        @PutMapping("/updateQuantity/{cartId}")
        public ResponseEntity<ResponseDto> updateQuantityById(@Valid @PathVariable int cartId, @RequestParam int quantity) {
            Optional<CartModel> updatedCart = cartIService.updateQuantity(cartId, quantity);
            ResponseDto responseDTO = new ResponseDto("Updated by quntity successfully", updatedCart);
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }

        //Method to delete book details by using bookId
        @DeleteMapping("/deleteById/{cartId}")
        public ResponseEntity<ResponseDto> deleteById(@PathVariable int cartId) {
            List<CartModel> updatedData = cartIService.deleteById(cartId);
            ResponseDto responseDTO = new ResponseDto("Id deleted successfully", updatedData);
            return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
        }
}
