package com.megazone.springbootbackend.sample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Kafka Producer 샘플</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : KafkaProducer</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/07</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/07 3:24 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Service
@RequiredArgsConstructor
public class KafkaProducer {

//  @Value(value = "${message.topic.default}")
//  private String topicName;
//  private final KafkaTemplate<String, String> kafkaTemplate;
//
//  public void sendMessage(String message) {
//    this.kafkaTemplate.send(this.topicName, message);
//  }
//
//  public void sendMessage(String topicName, String message) {
//    this.kafkaTemplate.send(topicName, message);
//  }
}
