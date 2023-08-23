package com.leonardozw.shippingapi.web.dto;

import com.leonardozw.shippingapi.domain.model.Status;

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
