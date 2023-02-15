package com.megazone.springbootbackend.community.kafka;

import com.megazone.springbootbackend.community.model.dto.PostViewCountDto;
import com.megazone.springbootbackend.community.util.CommonJsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {
//    @Value("${spring.kafka.consumer.bootstrap-servers}")
//    private String bootstrapServer;
//
//    @Bean
//    public Map<String,Object> postViewConsumerConfigs() {
//        return CommonJsonDeserializer.getStringObjectMap(bootstrapServer);
//    }
//
//    @Bean
//    public ConsumerFactory<String, PostViewCountDto> postViewCountDTO_ConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(postViewConsumerConfigs());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, PostViewCountDto> postViewCountListener() {
//        ConcurrentKafkaListenerContainerFactory<String, PostViewCountDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(postViewCountDTO_ConsumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public StringJsonMessageConverter jsonConverter() {
//        return new StringJsonMessageConverter();
//    }

}
