package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.services.IOrderService;


import com.example.bookstore.usermodel.OrderModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    IOrderService iOrderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDto> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderModel order = iOrderService.orderItem(orderDTO);
        ResponseDto responseDto = new ResponseDto("Order details are submitted!", order, null);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/orderservice/{orderId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int orderId) {
        OrderModel order = iOrderService.getOrder(orderId);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Get call for Id successful", order), HttpStatus.OK);

    }

    @PutMapping("/orderService/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int orderId , @RequestParam String token) {
        OrderModel order = iOrderService.cancelOrder(orderId,token);
        return new ResponseEntity<ResponseDto>(new ResponseDto("Order cancelled successfully", order), HttpStatus.OK);
    }
    //Method to update the quantity of books
    @PutMapping("/update/{orderId}")
    public ResponseEntity<ResponseDto> updateById(@Valid @PathVariable int orderId,@RequestBody OrderDTO orderDTO) {
      OrderModel updated = iOrderService.updateById(orderId, orderDTO);
        ResponseDto responseDTO = new ResponseDto("Updated by id", updated);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> getAll() {
        List<OrderModel> orderModels = iOrderService.getAll();
        ResponseDto responseDTO = new ResponseDto("Get all orders successfully", orderModels);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
}

