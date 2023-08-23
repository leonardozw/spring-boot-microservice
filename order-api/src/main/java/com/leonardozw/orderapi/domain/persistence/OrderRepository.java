package com.leonardozw.orderapi.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardozw.orderapi.domain.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
    Optional<Order> findByCustomer(String customer);
    Optional<Order> findByProduct(String product);
}
