package com.leonardozw.inventoryapi.web.dto;

import com.leonardozw.inventoryapi.domain.model.Status;

public record OrderResDTO(
        Long id,
        String product,
        String customer,
        String shippingAddress,
        Double price,
        Integer quantity,
        String createdAt,
        String lastUpdatedAt,
        Status status) {
}
