package com.ratz.inventoryservice.service.impl;

import com.ratz.inventoryservice.repository.InventoryRepository;
import com.ratz.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {

        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
