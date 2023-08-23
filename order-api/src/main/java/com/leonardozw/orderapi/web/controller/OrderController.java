package com.leonardozw.orderapi.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardozw.orderapi.domain.service.OrderService;
import com.leonardozw.orderapi.web.dto.OrderReqDTO;
import com.leonardozw.orderapi.web.dto.OrderResDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    
    private final OrderService orderService;


    @PostMapping("create")
    public ResponseEntity<OrderResDTO> createOrder(@RequestBody OrderReqDTO orderReqDTO){
        return ResponseEntity.ok(orderService.createOrder(orderReqDTO));
    }

    @GetMapping("all")
    public ResponseEntity<List<OrderResDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResDTO> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("customer/{customer}")
    public ResponseEntity<OrderResDTO> getOrderByCostumer(@PathVariable String customer){
        return ResponseEntity.ok(orderService.getOrderByCustomer(customer));
    }

    @GetMapping("product/{product}")
    public ResponseEntity<OrderResDTO> getOrderByProduct(@PathVariable String product){
        return ResponseEntity.ok(orderService.getOrderByProduct(product));
    }

    @PostMapping("update/{id}")
    public ResponseEntity<OrderResDTO> updateOrder(@PathVariable Long id, @RequestBody OrderReqDTO orderReqDTO){
        return ResponseEntity.ok(orderService.updateOrder(id, orderReqDTO));
    }

    @GetMapping("process/{id}")
    public ResponseEntity<OrderResDTO> processOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.processOrder(id));
    }

    @GetMapping("ship/{id}")
    public ResponseEntity<OrderResDTO> shipOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.shipOrder(id));
    }

    @GetMapping("delivered/{id}")
    public ResponseEntity<OrderResDTO> deliveredOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.deliveredOrder(id));
    }

    @DeleteMapping("cancel/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
