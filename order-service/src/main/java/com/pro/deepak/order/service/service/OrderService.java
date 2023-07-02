package com.pro.deepak.order.service.service;

import com.pro.deepak.order.service.dto.InventoryResponse;
import com.pro.deepak.order.service.dto.OrderItemsDTO;
import com.pro.deepak.order.service.dto.OrderRequest;
import com.pro.deepak.order.service.model.Order;
import com.pro.deepak.order.service.model.OrderItems;
import com.pro.deepak.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        order.setOrderItemsList(orderRequest.getOrderItemsDTOList().stream().map(orderItemsDTO -> mapToDTO(orderItemsDTO)).toList());


        List<String> skucodes = order.getOrderItemsList()
                .stream()
                .map(orderItems -> orderItems.getSkucode())
                .toList();


        //inventory service and place order if product is in stock
        InventoryResponse[] contains = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skucode",skucodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        Boolean res = Arrays.stream(contains).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if(!res)
            throw  new IllegalStateException("product is not in stock");
        orderRepository.save(order);
    }

    private OrderItems mapToDTO(OrderItemsDTO orderItemsDTO){
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDTO.getPrice());
        orderItems.setQuantity(orderItemsDTO.getQuantity());
        orderItems.setSkucode(orderItemsDTO.getSkucode());
        return orderItems;
    }
}
