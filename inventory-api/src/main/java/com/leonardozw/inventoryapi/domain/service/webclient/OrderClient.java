package com.leonardozw.inventoryapi.domain.service.webclient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import com.leonardozw.inventoryapi.web.dto.OrderResDTO;

public interface OrderClient {
    
    @GetExchange("/api/v1/order/process/{id}")
    OrderResDTO processOrder(@PathVariable Long id);
}
