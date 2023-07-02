package com.pro.deepak.inventory.service.service;

import com.pro.deepak.inventory.service.dto.InventoryResponse;
import com.pro.deepak.inventory.service.model.Inventory;
import com.pro.deepak.inventory.service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly=true)
    public List<InventoryResponse> isInStock(List<String> skucodes){
        return inventoryRepository.findBySkuCodeIn(skucodes).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skucode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }
}
