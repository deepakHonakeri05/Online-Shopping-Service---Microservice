package com.pro.deepak.order.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsDTO {

    private long id;
    private String skucode;
    private BigDecimal price;
    private Integer quantity;
}
