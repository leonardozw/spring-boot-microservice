package com.leonardozw.shippingapi.domain.service.webclient;

import com.leonardozw.shippingapi.web.dto.OrderResDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface OrderClient {

    @GetExchange("/api/v1/order/ship/{id}")
    OrderResDTO shipOrder(@PathVariable Long id);

    @GetExchange("/api/v1/order/delivered/{id}")
    OrderResDTO deliveredOrder(@PathVariable Long id);
}
