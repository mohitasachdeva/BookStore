package com.example.bookstore.services;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.USerException;
import com.example.bookstore.repository.BookRepo;
import com.example.bookstore.repository.IOrderRepo;
import com.example.bookstore.repository.IRepo;
import com.example.bookstore.usermodel.BookModel;

import com.example.bookstore.usermodel.OrderModel;
import com.example.bookstore.usermodel.UserModel;
import com.example.bookstore.util.Token;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService implements IOrderService {
    @Autowired
    IRepo iRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    Token tokenUtility;
    @Autowired
    IOrderRepo orderRepo;
    @Autowired
    IEmail iEmail;

    public OrderModel orderItem(OrderDTO orderDTO) {
        Optional<UserModel> user = iRepo.findById(orderDTO.getUserId());
        Optional<BookModel> book = bookRepo.findById(orderDTO.getBookId());

        float totalPrice = book.get().getPrice() * orderDTO.getQuantity();
        if (user.isPresent()) {
            OrderModel order = new OrderModel(user.get(), book.get(), totalPrice, orderDTO.quantity, orderDTO.address, orderDTO.cancel);
            orderRepo.save(order);
//            String token = tokenUtility.createToken(order.getOrderId());
//            Email emailModel = new Email(user.get().getEmail(), "Order Placed successfully ", "Hii." + user.get().getFirstName());
//            iEmail.sendMail(emailModel);
            return order;
        } else {
            throw new USerException("User id or book id did not match! Please check and try again!");
        }


    }

    @Override
    public OrderModel getOrder(int orderId) {

        Optional<UserModel> optionalUserData = iRepo.findById(orderId);
        if (optionalUserData.isPresent()) {
            return orderRepo.findById(orderId).get();
        } else {
            throw new USerException("id not found");
        }
    }

    @Override


    public OrderModel cancelOrder(int orderId, String token) {
        int userId = tokenUtility.decodeToken(token);
        Optional<UserModel> optionalUserData = iRepo.findById(userId);
        log.info(String.valueOf(userId));
        if (optionalUserData.isPresent()) {
            OrderModel order = orderRepo.findById(orderId).get();
            order.setCancel(true);
            BookModel book = order.getBook();
            book.setQuantity(book.getQuantity() + order.getQuantity());
            bookRepo.save(book);
            order.setBook(book);
            return orderRepo.save(order);
        } else {
            throw new USerException(token);
        }
    }

    public OrderModel updateById(int orderId, OrderDTO orderDto){
        Optional<UserModel> user = iRepo.findById(orderDto.userId);
        Optional<BookModel> book = bookRepo.findById(orderDto.bookId);
        float totalPrice = book.get().getPrice()* orderDto.quantity;
        if (user.isPresent()) {
            OrderModel order = new OrderModel(user.get(),book.get(),totalPrice,orderDto.quantity,orderDto.address,orderDto.cancel);
            order.setOrderId(orderId);
            return orderRepo.save(order);
        } else {
            throw new USerException("User id or book id did not match! Please check and try again!");
        }
    }
    public List<OrderModel> getAll(){
        List<OrderModel>orderModels=orderRepo.findAll();
        return orderModels;
    }
}
