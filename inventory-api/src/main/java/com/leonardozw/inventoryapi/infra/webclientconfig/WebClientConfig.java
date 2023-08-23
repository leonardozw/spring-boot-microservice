package com.leonardozw.inventoryapi.infra.webclientconfig;

import com.leonardozw.inventoryapi.domain.service.webclient.OrderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    
    @Bean
    OrderClient orderClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(OrderClient.class);
    }
}
