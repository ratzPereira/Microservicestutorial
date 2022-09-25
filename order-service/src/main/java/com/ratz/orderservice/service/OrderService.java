package com.ratz.orderservice.service;

import com.ratz.orderservice.dto.OrderRequestDTO;

public interface OrderService {

    String placeOrder(OrderRequestDTO orderRequestDTO);
}
