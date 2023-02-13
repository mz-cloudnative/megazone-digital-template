package com.megazone.springbootbackend.handler;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service(value = "KafkaCustomErrorHandler")
public class KafkaCustomErrorHandler implements ConsumerAwareListenerErrorHandler {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  private static String DEAD_TOPIC_NAME = "loc-dead-topic";

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
    log.error("[KafkaErrorHandler] KafkaMessage=[" + message.getPayload() + "], errorMessage=[" + exception.getMessage() + "]");
    kafkaTemplate.send(DEAD_TOPIC_NAME, exception.getMessage());
    return null;
  }

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {

    log.error("[KafkaErrorHandler] KafkaMessage=[" + message.getPayload() + "], errorMessage=[" + exception.getMessage() + "]");

    ConsumerRecord<String, Object> record = (ConsumerRecord<String, Object>) message.getPayload();
    log.error("[KafkaErrorHandler] topic=[" + record.topic() + "], value=[" + record.value() + "]");
    kafkaTemplate.send(DEAD_TOPIC_NAME, record.value());
    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
    offsets.put(
        new TopicPartition((record.topic()), record.partition()),
        new OffsetAndMetadata(record.offset() + 1, null)
    );
    consumer.commitSync(offsets);
    return null;
  }
}
