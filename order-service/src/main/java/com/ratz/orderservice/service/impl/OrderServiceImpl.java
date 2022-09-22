package com.ratz.orderservice.service.impl;

import com.ratz.orderservice.dto.OrderLineItemsDTO;
import com.ratz.orderservice.dto.OrderRequestDTO;
import com.ratz.orderservice.model.Order;
import com.ratz.orderservice.model.OrderLineItems;
import com.ratz.orderservice.repository.OrderRepository;
import com.ratz.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequestDTO orderRequestDTO) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orders = orderRequestDTO.getOrderLineItemsDTOS().stream().map(this::mapToDTO).toList();

        order.setOrderLineItems(orders);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());

        return orderLineItems;
    }
}
