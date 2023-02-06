package com.megazone.springbootbackend.sample;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : Testcontainers 설정</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : AbstractContainerBaseTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/30</li>
 * <li>설     명 : JUnit 테스트 시 컨테이너 설정. 즉, 로컬 환경과 관계없이 테스트 가능.</li>
 * <li>           현재는 redis 컨테이너 설정만 잡혀있으나, 추가로 DB 컨테이너도 설정 가능.</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/30 1:16 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@Testcontainers
public abstract class AbstractContainerBaseTest {
  //로컬의 도커 데스크탑이 실행중이어야 한다.

  private static final String REDIS_IMAGE = "redis:6-alpine";

  @Container
  static final GenericContainer REDIS_CONTAINER;

  static {
    REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
        .withExposedPorts(6379)
        .withReuse(true);
    REDIS_CONTAINER.start();
  }

  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", REDIS_CONTAINER::getHost);
    registry.add("spring.redis.port", () -> "" + REDIS_CONTAINER.getMappedPort(6379));
  }
}
