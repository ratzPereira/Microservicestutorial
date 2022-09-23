package com.ratz.orderservice.service.impl;

import com.ratz.orderservice.dto.InventoryResponseDTO;
import com.ratz.orderservice.dto.OrderLineItemsDTO;
import com.ratz.orderservice.dto.OrderRequestDTO;
import com.ratz.orderservice.model.Order;
import com.ratz.orderservice.model.OrderLineItems;
import com.ratz.orderservice.repository.OrderRepository;
import com.ratz.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequestDTO orderRequestDTO) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequestDTO.getOrderLineItemsDTOS().stream().map(this::mapToDTO).toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        //Call inventory service and place order if products in stock
        InventoryResponseDTO[] result = webClient
                .get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponseDTO::isInStock);

        if (Boolean.TRUE.equals(allProductsInStock)) {

            orderRepository.save(order);

        } else {
            throw new IllegalArgumentException("Product not in stock");
        }

    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {

        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());

        return orderLineItems;
    }
}
