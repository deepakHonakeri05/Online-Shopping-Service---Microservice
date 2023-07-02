package com.pro.deepak.inventory.service.controller;

import com.pro.deepak.inventory.service.dto.InventoryResponse;
import com.pro.deepak.inventory.service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInstock(@RequestParam(value = "skucode") List<String> skucodes){
        return inventoryService.isInStock(skucodes);
    }
}
