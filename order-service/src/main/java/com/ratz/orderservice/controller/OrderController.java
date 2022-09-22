package com.ratz.orderservice.controller;

import com.ratz.orderservice.dto.OrderRequestDTO;
import com.ratz.orderservice.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final OrderServiceImpl orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

        orderService.placeOrder(orderRequestDTO);
        return "Order Done!";
    }
}
