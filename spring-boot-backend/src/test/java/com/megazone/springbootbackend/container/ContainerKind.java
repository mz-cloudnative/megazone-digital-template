package com.megazone.springbootbackend.container;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public enum ContainerKind {

  KAFKA(KafkaContainer.KAFKA_PORT, "confluentinc/cp-kafka:7.3.1.arm64"),
  REDIS(6379, "redis:6-alpine"),
  POSTGRES_SQL(PostgreSQLContainer.POSTGRESQL_PORT, "postgres:alpine");

  ContainerKind(Integer port, String image) {
    this.port = port;
    this.image = image;
  }

  public Integer getPort() {
    return port;
  }

  public String getImage() {
    return image;
  }

  private final Integer port;
  private final String image;
}
