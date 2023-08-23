package com.leonardozw.inventoryapi.domain.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.leonardozw.inventoryapi.domain.service.webclient.OrderClient;
import com.leonardozw.inventoryapi.web.dto.OrderResDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryService {

    private final OrderClient orderClient;

    private final KafkaTemplate<String, OrderResDTO> kafkaTemplate;

    @KafkaListener(topics = "order-created", groupId = "consumer")
    public void processOrder(OrderResDTO orderResDTO) {
        System.out.println("Received Message in group foo: " + orderResDTO.customer());
        orderClient.processOrder(orderResDTO.id());
        System.out.println("Order: " + orderResDTO.id() + " processed!");
        kafkaTemplate.send("order-processed", orderResDTO);
    }
    
}
