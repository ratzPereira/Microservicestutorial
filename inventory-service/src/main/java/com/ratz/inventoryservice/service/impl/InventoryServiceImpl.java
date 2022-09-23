package com.ratz.inventoryservice.service.impl;

import com.ratz.inventoryservice.dto.InventoryResponseDTO;
import com.ratz.inventoryservice.repository.InventoryRepository;
import com.ratz.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> isInStock(List<String> skuCode) {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponseDTO.builder().skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0).build()).toList();
    }
}
