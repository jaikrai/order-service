package com.javatech.os.api.controller;

import com.javatech.os.api.common.Payment;
import com.javatech.os.api.common.TransactionRequest;
import com.javatech.os.api.common.TransactionResponse;
import com.javatech.os.api.entity.Order;
import com.javatech.os.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);
    }
}