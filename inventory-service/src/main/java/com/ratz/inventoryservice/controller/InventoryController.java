package com.ratz.inventoryservice.controller;

import com.ratz.inventoryservice.dto.InventoryResponseDTO;
import com.ratz.inventoryservice.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    private List<InventoryResponseDTO> isInStock(@RequestParam List<String> skuCode) {

        return inventoryService.isInStock(skuCode);
    }
}
