package com.pro.deepak.order.service.controller;

import com.pro.deepak.order.service.dto.OrderRequest;
import com.pro.deepak.order.service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory" , fallbackMethod = "fallback")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed successfully";
    }

    public String fallback(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException){
        return "OOPS something went wrong!. Please order after sometime"+runtimeException;
    }
}
