package com.leonardozw.orderapi.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private String customer;
    private String shippingAddress;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime lastUpdatedAt;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(String product, String customer, String shippingAddress, Double price, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.price = price;
        this.quantity = quantity;
        createdAt = LocalDateTime.now();
        status = Status.RECEIVED;
    }

    public Double getTotal() {
        return price * quantity;
    }
}
