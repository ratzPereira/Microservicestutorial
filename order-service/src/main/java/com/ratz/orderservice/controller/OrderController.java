package com.ratz.orderservice.controller;

import com.ratz.orderservice.dto.OrderRequestDTO;
import com.ratz.orderservice.service.impl.OrderServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {


    private final OrderServiceImpl orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

       return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequestDTO));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDTO orderRequestDTO, RuntimeException exception){

        return CompletableFuture.supplyAsync(() -> "Oops! something went wrong, come back later!");
    }
}
