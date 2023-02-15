package com.megazone.springbootbackend.community.kafka;

import com.megazone.springbootbackend.community.model.dto.PostViewCountDto;
import com.megazone.springbootbackend.community.util.CommonJsonSerializer;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServer;
//
//    @Bean
//    public Map<String,Object> postViewProducerConfigs() {
//        return CommonJsonSerializer.getStringObjectMap(bootstrapServer);
//    }
//
//    @Bean
//    public ProducerFactory<String, PostViewCountDto> postViewCountDTOProducerFactory() {
//        return new DefaultKafkaProducerFactory<>(postViewProducerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, PostViewCountDto> postViewDTOKafkaTemplate() {
//        return new KafkaTemplate<>(postViewCountDTOProducerFactory());
//    }
}
