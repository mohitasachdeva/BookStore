package com.example.bookstore.services;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.usermodel.OrderModel;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderModel orderItem(OrderDTO orderDTO);
    OrderModel getOrder(int orderId);


    OrderModel cancelOrder(int orderId, String token);

OrderModel updateById(int orderId, OrderDTO orderDTO);
    List<OrderModel> getAll();
}
