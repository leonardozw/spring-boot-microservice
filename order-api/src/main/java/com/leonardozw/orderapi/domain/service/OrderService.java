package com.leonardozw.orderapi.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.leonardozw.orderapi.domain.model.Order;
import com.leonardozw.orderapi.domain.model.Status;
import com.leonardozw.orderapi.domain.persistence.OrderRepository;
import com.leonardozw.orderapi.web.dto.OrderReqDTO;
import com.leonardozw.orderapi.web.dto.OrderResDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    
    private OrderRepository orderRepository;

    private final DateTimeFormatter formatter;

    private KafkaTemplate<String, OrderResDTO> kafkaTemplate;

    @Cacheable("orders")
    public OrderResDTO createOrder(OrderReqDTO orderDTO){
        Order order = convertToOrder(orderDTO);
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        kafkaTemplate.send("order-created", convertToResDTO(order));
        return convertToResDTO(order);
    }

    public List<OrderResDTO> getAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToResDTO).collect(Collectors.toList());
    }

    @Cacheable("orders")
    public OrderResDTO getOrderById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return convertToResDTO(order);
    }

    @Cacheable("orders")
    public OrderResDTO getOrderByCustomer(String customer){
        Order order = orderRepository.findByCustomer(customer).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return convertToResDTO(order);
    }

    @Cacheable("orders")
    public OrderResDTO getOrderByProduct(String product){
        Order order = orderRepository.findByProduct(product).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return convertToResDTO(order);
    }

    @CachePut(value = "orders", key = "#id")
    public OrderResDTO updateOrder(Long id, OrderReqDTO orderDTO){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setProduct(orderDTO.product());
        order.setCustomer(orderDTO.customer());
        order.setShippingAddress(orderDTO.shippingAddress());
        order.setPrice(orderDTO.price());
        order.setQuantity(orderDTO.quantity());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return convertToResDTO(order);
    }

    @CachePut(value = "orders", key = "#id")
    public OrderResDTO processOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(Status.PROCESSING);
        order.setShippedAt(LocalDateTime.now());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return convertToResDTO(order);
    }

    @CachePut(value = "orders", key = "#id")
    public OrderResDTO shipOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(Status.SHIPPED);
        order.setShippedAt(LocalDateTime.now());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return convertToResDTO(order);
    }

    @CachePut(value = "orders", key = "#id")
    public OrderResDTO deliveredOrder(Long id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(Status.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        order.setLastUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return convertToResDTO(order);
    }

    @CacheEvict(value = "orders", key = "#id")
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    private Order convertToOrder(OrderReqDTO orderDTO) {
        return new Order(orderDTO.product(), orderDTO.customer(), orderDTO.shippingAddress(), orderDTO.price(), orderDTO.quantity());
    }

    private OrderResDTO convertToResDTO(Order order) {
        String createdAt = order.getCreatedAt().format(formatter);
        String lastUpdatedAt = order.getLastUpdatedAt().format(formatter);
        return new OrderResDTO(
            order.getId(),
            order.getProduct(),
            order.getCustomer(),
            order.getShippingAddress(),
            order.getPrice(),
            order.getQuantity(),
            createdAt,
            lastUpdatedAt,
            order.getStatus());
    }
}
