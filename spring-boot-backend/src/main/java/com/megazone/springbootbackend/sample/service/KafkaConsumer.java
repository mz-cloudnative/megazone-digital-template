package com.megazone.springbootbackend.sample.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Kafka Consumer 샘플</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : KafkaConsumer</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/07</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/07 3:29 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
//@Service
//@Slf4j
//@Getter
public class KafkaConsumer {

//  private ConsumerRecord<?, ?> consumerRecord = null;
//
//  @KafkaListener(topics = "${message.topic.default}", groupId = "${spring.kafka.consumer.group-id}")
//  public void consume(ConsumerRecord<?, ?> consumerRecord) {
//    log.info("received payload='{}'", consumerRecord.toString());
//    this.consumerRecord = consumerRecord;
//    log.info("Consumed message : {}", consumerRecord.value());
//  }

}
