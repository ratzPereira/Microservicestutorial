package com.ratz.inventoryservice.controller;

import com.ratz.inventoryservice.service.impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    private boolean isInStock(@PathVariable String skuCode) {

        return inventoryService.isInStock(skuCode);
    }
}
