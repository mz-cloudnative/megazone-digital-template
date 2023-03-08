package com.megazone.springbootbackend.family.service;

import com.megazone.springbootbackend.config.DataSourceConfig;
import com.megazone.springbootbackend.container.ContainerBase;
import com.megazone.springbootbackend.container.ContainerKind;
import com.megazone.springbootbackend.family.model.domain.SpecialDay;
import com.megazone.springbootbackend.family.model.event.SpecialDayEvent;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : FamilyServiceTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/27</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/27 5:03 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@SpringBootTest
@Import(DataSourceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@RecordApplicationEvents
class FamilyServiceTest {

  @Container
  static final GenericContainer<?> POSTGRES_SQL_CONTAINER_MASTER =
      ContainerBase.containerCreate(ContainerKind.POSTGRES_SQL);

  @Autowired
  FamilyService familyService;
  @Autowired
  ApplicationEvents events;

  /**
   * @param registry: 스프링부트 컨텍스트
   * @apiNote 생성한 컨테이너에 대해서 스프링부트 컨텍스트에 설정을 해주기 위함.
   * @author mz01-ohyunbk
   * @since 2023/02/15 1:15 PM
   */
  @DynamicPropertySource
  static void init(DynamicPropertyRegistry registry) {
    ContainerBase.overrideProps(registry
        , List.of(POSTGRES_SQL_CONTAINER_MASTER));
  }

  @Test
  void addEvent() {
    //given + when
    familyService.addEvent(SpecialDay.builder()
        .name("테스트")
        .build());
    //then
    Assertions.assertThat(events.stream(SpecialDayEvent.class).count()).isEqualTo(1);
  }
}