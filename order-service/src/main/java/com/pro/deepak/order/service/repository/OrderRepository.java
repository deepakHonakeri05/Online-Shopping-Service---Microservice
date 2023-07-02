package com.pro.deepak.order.service.repository;

import com.pro.deepak.order.service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
