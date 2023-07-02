package com.pro.deepak.inventory.service.repository;

import com.pro.deepak.inventory.service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    public List<Inventory> findBySkuCodeIn(List<String> skucodes);
}
