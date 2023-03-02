package com.megazone.springbootbackend;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.megazone.springbootbackend.container.ContainerBase;
import com.megazone.springbootbackend.container.ContainerKind;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringBootBackendApplicationTests {

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

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Autowired
  ConfigurableEnvironment environment;

  @Test
  @DisplayName("DB 연결에 성공한다.")
  public void shouldConnectToDatabase() {
    assertThat(jdbcTemplate.queryForObject("SELECT version()", String.class))
        .contains("PostgreSQL");
  }

  @Test
  @DisplayName("KAFKA, DB, Redis 환경변수가 정상적으로 변경된다.")
  public void propertiesAreAvailable() {

    assertThat(environment.getProperty("message.topic.default")).isEqualTo(
        "testcontainers-test-topic");
    assertThat(environment.getProperty("spring.kafka.properties.bootstrap.servers"))
        .isEqualTo(((KafkaContainer) KAFKA_CONTAINER).getBootstrapServers());

    assertThat(environment.getProperty("spring.datasource.master.hikari.jdbc-url"))
        .isEqualTo(((PostgreSQLContainer) POSTGRES_SQL_CONTAINER).getJdbcUrl());
    assertThat(environment.getProperty("spring.datasource.master.hikari.username"))
        .isEqualTo(((PostgreSQLContainer) POSTGRES_SQL_CONTAINER).getUsername());
    assertThat(environment.getProperty("spring.datasource.master.hikari.password"))
        .isEqualTo(((PostgreSQLContainer) POSTGRES_SQL_CONTAINER).getPassword());

    assertThat(environment.getProperty("spring.redis.host"))
        .isEqualTo(REDIS_CONTAINER.getHost());
    assertThat(environment.getProperty("spring.redis.port"))
        .isEqualTo(REDIS_CONTAINER.getMappedPort(6379).toString());
  }

}
