package com.leonardozw.inventoryapi.infra.kafkaconfig;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import com.leonardozw.inventoryapi.web.dto.OrderResDTO;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderResDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), errorHandlingDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderResDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderResDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(GROUP_ID_CONFIG,"consumer");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        return props;
    }

    private ErrorHandlingDeserializer<OrderResDTO> errorHandlingDeserializer() {
        JsonDeserializer<OrderResDTO> jsonDeserializer = new JsonDeserializer<>(OrderResDTO.class, false);
        jsonDeserializer.setUseTypeHeaders(false);
        return new ErrorHandlingDeserializer<>(jsonDeserializer);
    }
}
