package com.megazone.springbootbackend.sample;

import com.megazone.springbootbackend.config.DataSourceConfig;
import com.megazone.springbootbackend.container.ContainerBase;
import com.megazone.springbootbackend.container.ContainerKind;
import com.megazone.springbootbackend.sample.model.dto.SampleDataRequest;
import com.megazone.springbootbackend.sample.model.dto.SampleDataResponse;
import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import com.megazone.springbootbackend.sample.repository.SampleCustomRepository;
import com.megazone.springbootbackend.sample.repository.SampleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : sample JPA 테스트코드</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : SampleJpaTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/15</li>
 * <li>설     명 : DataJpaTest 어노테이션을 사용하여 테스트. 이로 인하여 테스트에 필요한 설정파일과 컴포넌트를 직접 설정을 잡아야함.</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/15 3:08 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@DataJpaTest
@Import({DataSourceConfig.class, JPATestConfig.class}) //DataSource 이중화와 QueryDSL로 인하여 설정 파일 추가
@ComponentScan(basePackageClasses = {
    SampleRepository.class, SampleCustomRepository.class
}) //테스트에 필요한 컴포넌트 직접 설정
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//embeded h2를 사용하지 않고 postgresSQL Testcontainers를 사용하기 위한 설정.
@Testcontainers
class SampleJpaTest {

  @Autowired
  SampleRepository sampleRepository;

  @Autowired
  SampleCustomRepository sampleCustomRepository;

  @Container
  static final GenericContainer POSTGRES_SQL_CONTAINER =
      ContainerBase.containerCreate(ContainerKind.POSTGRES_SQL);

  /**
   * @param registry: 스프링부트 컨텍스트
   * @apiNote 생성한 컨테이너에 대해서 스프링부트 컨텍스트에 설정을 해주기 위함.
   * @author mz01-ohyunbk
   * @since 2023/02/15 1:15 PM
   */
  @DynamicPropertySource
  static void init(DynamicPropertyRegistry registry) {
    ContainerBase.overrideProps(registry
        , List.of(POSTGRES_SQL_CONTAINER));
  }

  @BeforeEach
  void init() {
    sampleRepository.save(
        SampleJpaEntity.requestToEntity(
            SampleDataRequest.builder()
                .sampleId(1L)
                .sampleName("test1")
                .build()
        )
    );

    sampleRepository.save(
        SampleJpaEntity.requestToEntity(
            SampleDataRequest.builder()
                .sampleId(2L)
                .sampleName("test2")
                .build()
        )
    );
  }

  @Test
  @DisplayName("샘플 이름 조회 테스트")
  void getName_test() {
    final var sampleData = sampleCustomRepository.findAllByName("test1");

    Assertions.assertThat(sampleData).isNotNull();
  }

  @Test
  @DisplayName("샘플 조회 목록 테스트")
  void sampleList_test() {
    final var sampleData = sampleRepository.findAll().stream()
        .map(SampleDataResponse::entityToResponse)
        .collect(Collectors.toList());

    Assertions.assertThat(sampleData).hasSize(2);
  }

}
