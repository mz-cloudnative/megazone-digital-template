package com.megazone.springbootbackend.family;

import com.megazone.springbootbackend.config.DataSourceConfig;
import com.megazone.springbootbackend.container.ContainerBase;
import com.megazone.springbootbackend.container.ContainerKind;
import com.megazone.springbootbackend.family.model.domain.Event;
import com.megazone.springbootbackend.family.model.domain.Family;
import com.megazone.springbootbackend.family.model.entity.Alarm;
import com.megazone.springbootbackend.family.model.entity.EventEntity;
import com.megazone.springbootbackend.family.model.entity.FamilyEntity;
import com.megazone.springbootbackend.family.repository.EventRepository;
import com.megazone.springbootbackend.family.repository.FamilyRepository;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : EventRepositoryTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/01</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/01 2:00 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@DataJpaTest
@Import(DataSourceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class FamilyJpaTest {

  @Container
  static final GenericContainer<?> POSTGRES_SQL_CONTAINER =
      ContainerBase.containerCreate(ContainerKind.POSTGRES_SQL);

  @Autowired
  EntityManager entityManager;

  @Autowired
  FamilyRepository familyRepository;

  @Autowired
  EventRepository eventRepository;

  Family family;
  Event event;

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
    event =
        Event.builder()
            .name("insert_test1")
            .startDateTime(LocalDateTime.now())
            .endDateTime(LocalDateTime.now())
            .alarm(Alarm.EMAIL)
            .alarmDateTime(LocalDateTime.now())
            .build();
    family =
        Family.builder()
            .name("insert_test1")
            .memberId(1L)
            .build();
  }

  @Test
  @DisplayName("연관관계 테스트")
  @Transactional
  void mapping_test() {
    //given
    FamilyEntity familyEntity = familyRepository.save(FamilyEntity.fromDomainEntity(family));
    event.setFamily(familyEntity.toDomainEntity());
    EventEntity eventEntity = eventRepository.save(EventEntity.fromDomainEntity(event));

    entityManager.flush();
    entityManager.clear();

    //when + then
    eventRepository.findById(eventEntity.getId())
        .map(EventEntity::toDomainEntity)
        .ifPresentOrElse(
            (r) -> {
              Assertions.assertEquals(family.getName(), r.getFamily().getName());
              Assertions.assertEquals(event.getAlarm(), r.getAlarm());
            }
            , () -> System.out.println("패밀리가 존재하지 않습니다.")
        );
    familyRepository.findById(familyEntity.getId())
        .map(FamilyEntity::toDomainEntity)
        .ifPresentOrElse(
            (r) -> Assertions.assertEquals(1, r.getEvent().size())
            , () -> System.out.println("이벤트가 존재하지 않습니다.")
        );
  }
}