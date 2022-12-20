package com.example.bookstore.services;

import com.example.bookstore.dto.CartDto;
import com.example.bookstore.exception.USerException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.CartRepo;
import com.example.bookstore.repository.IRepo;
import com.example.bookstore.usermodel.BookModel;
import com.example.bookstore.usermodel.CartModel;
import com.example.bookstore.usermodel.UserModel;
import com.example.bookstore.util.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
        @Autowired
        CartRepo cartRepository;
        @Autowired
        IRepo userRepository;
        @Autowired
        BookRepo bookRepository;
        @Autowired
        Token tokenUtil;


        @Override
        public CartModel addToCart(CartDto cartDTO) {
            Optional<UserModel> user = userRepository.findById(cartDTO.getUserID());
            Optional<BookModel> book = bookRepository.findById(cartDTO.getBookId());
            if (user.isPresent() && book.isPresent()) {
                CartModel addedItem = new CartModel(book.get(), user.get(), cartDTO.getQuantity());
                cartRepository.save(addedItem);
                return addedItem;
            } else throw new USerException("UserId or bookId not present please provide correct details");
        }

        @Override

        public Optional<CartModel> getById(int id) {
            Optional<CartModel> cart = cartRepository.findById(id);
            if (cart.isPresent()) {
                return cart;
            } else throw new USerException("Cart Id not  present");
        }

        @Override


        public List<CartModel> getAll() {
            if (!cartRepository.findAll().isEmpty()) {
                return cartRepository.findAll();
            } else throw new USerException("No Item Present in the Cart");
        }

        @Override

        public CartModel updateById(int cartId, CartDto cartDTO) {
            Optional<CartModel> cart = cartRepository.findById(cartId);
            Optional<BookModel> book = bookRepository.findById(cartDTO.getBookId());
            Optional<UserModel> user = userRepository.findById(cartDTO.getUserID());
            if (cart.isPresent() && book.isPresent() && user.isPresent()) {
                CartModel updatedCart = new CartModel(cartId, book.get(),
                        user.get(), cartDTO.getQuantity());
                cartRepository.save(updatedCart);
                return updatedCart;
            } else throw new USerException("Id not present to update quantity");
        }

        @Override

        public Optional<CartModel> updateQuantity(int cartId, int quantity) {
            Optional<CartModel> cartModel = cartRepository.findById(cartId);
            if (cartModel.isPresent()) {
                cartModel.get().setQuantity(quantity);
                CartModel cart = new CartModel(cartModel.get().getCartId(), cartModel.get().getBook(),
                        cartModel.get().getUser(), cartModel.get().getQuantity());
                cartRepository.save(cart);
                return cartModel;
            } else throw new USerException("Id not present to update quantity");
        }

        @Override

        public List<CartModel> deleteById(int cartId) {
            if (cartRepository.findById(cartId).isPresent()) {
                cartRepository.deleteById(cartId);
                return cartRepository.findAll();
            } else {
                throw new USerException("Id not present in the list to delete");
            }
        }

    }

