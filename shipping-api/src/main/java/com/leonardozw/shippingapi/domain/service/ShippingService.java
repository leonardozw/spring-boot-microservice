package com.leonardozw.shippingapi.domain.service;

import com.leonardozw.shippingapi.domain.service.webclient.OrderClient;
import com.leonardozw.shippingapi.web.dto.OrderResDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingService {

    private final OrderClient orderClient;

    private final KafkaTemplate<String, OrderResDTO> kafkaTemplate;

    @KafkaListener(topics = "order-processed", groupId = "consumer")
    public void shipOrder(OrderResDTO orderResDTO) {
        System.out.println("Shipping order: " + orderResDTO);
        orderClient.shipOrder(orderResDTO.id());
        System.out.println("Order: " + orderResDTO.id() + " shipped!");
        kafkaTemplate.send("order-shipped", orderResDTO);
    }

    @KafkaListener(topics = "order-shipped", groupId = "consumer")
    public void deliveredOrder(OrderResDTO orderResDTO) {
        System.out.println("Delivering order: " + orderResDTO.id() + " to customer: " + orderResDTO.customer());
        orderClient.deliveredOrder(orderResDTO.id());
        System.out.println("Order delivered!");
    }
}
