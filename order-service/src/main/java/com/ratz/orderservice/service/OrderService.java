package com.ratz.orderservice.service;

import com.ratz.orderservice.dto.OrderRequestDTO;

public interface OrderService {

    void placeOrder(OrderRequestDTO orderRequestDTO);
}
