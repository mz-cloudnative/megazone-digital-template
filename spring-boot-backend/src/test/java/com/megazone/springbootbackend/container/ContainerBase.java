package com.megazone.springbootbackend.container;

import java.util.List;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : KafkaContainerBase</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/13</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/13 5:55 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/

public class ContainerBase {

  public static GenericContainer<?> containerCreate(ContainerKind containerKind) {
    switch (containerKind) {
      case REDIS:
        return new GenericContainer(containerKind.getImage())
            .withExposedPorts(containerKind.getPort())
            .withReuse(true);
      case POSTGRES_SQL:
        return new PostgreSQLContainer<>(containerKind.getImage())
            .withExposedPorts(5432)
            .withReuse(true);
      case KAFKA:
        return new KafkaContainer(
            DockerImageName.parse(containerKind.getImage()))
            .withReuse(true);
    }
    throw new RuntimeException("해당하는 컨테이너 설정이 존재하지 않습니다.");
  }

  public static void overrideProps(DynamicPropertyRegistry registry,
      List<GenericContainer<?>> containerList) {
    for (GenericContainer<?> container : containerList) {
      if (ContainerKind.KAFKA.getImage().equals(container.getDockerImageName())) {
        KafkaContainer kafkaContainer = (KafkaContainer) container;
        registry.add("message.topic.default", () -> "testcontainers-test-topic");
        registry.add("spring.kafka.properties.bootstrap.servers",
            kafkaContainer::getBootstrapServers);
      }
      if (ContainerKind.POSTGRES_SQL.getImage().equals(container.getDockerImageName())) {
        PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) container;
        //Master
        registry.add("spring.datasource.master.hikari.jdbc-url",
            postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.master.hikari.username",
            postgreSQLContainer::getUsername);
        registry.add("spring.datasource.master.hikari.password",
            postgreSQLContainer::getPassword);
        //Slave
        registry.add("spring.datasource.slave.hikari.jdbc-url",
            postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.slave.hikari.username",
            postgreSQLContainer::getUsername);
        registry.add("spring.datasource.slave.hikari.password",
            postgreSQLContainer::getPassword);
      }
      if (ContainerKind.REDIS.getImage().equals(container.getDockerImageName())) {
        registry.add("spring.redis.host", container::getHost);
        registry.add("spring.redis.port", () -> container.getMappedPort(6379));
      }
    }
  }
}
