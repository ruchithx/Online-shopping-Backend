package com.example.Order.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class OrderController {

    @GetMapping("orders")
    public String getOrders(){
        return "All orders fetched by db ";
    }
}
