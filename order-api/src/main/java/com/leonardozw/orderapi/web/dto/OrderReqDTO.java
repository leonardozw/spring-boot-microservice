package com.leonardozw.orderapi.web.dto;

public record OrderReqDTO(String product, String customer, String shippingAddress, Double price, Integer quantity) {
    
}
