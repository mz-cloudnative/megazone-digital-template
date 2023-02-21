package com.megazone.springbootbackend.sample;

import com.megazone.springbootbackend.container.ContainerBase;
import com.megazone.springbootbackend.container.ContainerKind;
import com.megazone.springbootbackend.sample.service.KafkaConsumer;
import com.megazone.springbootbackend.sample.service.KafkaProducer;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Kafka sample 테스트코드</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : SampleKafkaTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/08</li>
 * <li>설     명 : springBootTest 어노테이션으로 인하여 모든 testcontainers 설정 </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/08 3:19 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Testcontainers
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class) //@Order에 의해 메소드별 실행순서 보장
@DisplayName("Kafka 테스트")
class SampleKafkaTest {

  @Autowired
  private KafkaProducer producer;
  @Autowired
  private KafkaConsumer consumer;
  @Autowired
  private KafkaAdmin admin;

  @Container
  static final GenericContainer<?> REDIS_CONTAINER =
      ContainerBase.containerCreate(ContainerKind.REDIS);
  @Container
  static final GenericContainer<?> POSTGRES_SQL_CONTAINER =
      ContainerBase.containerCreate(ContainerKind.POSTGRES_SQL);
  @Container
  static final GenericContainer<?> KAFKA_CONTAINER =
      ContainerBase.containerCreate(ContainerKind.KAFKA);

  /**
   * @param registry: 스프링부트 컨텍스트
   * @apiNote 생성한 컨테이너에 대해서 스프링부트 컨텍스트에 설정을 해주기 위함.
   * @author mz01-ohyunbk
   * @since 2023/02/15 1:15 PM
   */
  @DynamicPropertySource
  static void init(DynamicPropertyRegistry registry) {
    ContainerBase.overrideProps(registry
        , List.of(REDIS_CONTAINER, POSTGRES_SQL_CONTAINER, KAFKA_CONTAINER));
  }

  @Test
  @Order(value = 1) //테스트 실행 순서
  @DisplayName("pub test")
  void pub_and_sub_test() throws InterruptedException {
    //given
    final String testTopic = "testcontainers-test-topic";

    //when
    producer.sendMessage(testTopic, "testContainers TEST!");
    Thread.sleep(1000); //consumer가 메시지를 읽기 위하여 잠시 대기.

    //then
    Assertions.assertThat(consumer.getConsumerRecord().topic()).contains(testTopic);
  }

}
