package com.ratz.inventoryservice.service;

import com.ratz.inventoryservice.dto.InventoryResponseDTO;

import java.util.List;

public interface InventoryService {

    List<InventoryResponseDTO> isInStock(List<String> skuCode);
}
