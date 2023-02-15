package com.megazone.springbootbackend.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : QuerlDSL 설정</li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : JPATestConfig</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/02/15</li>
 * <li>설     명 : DataJpaTest에서 QuerlDSL 관련 빈 오류를 해결하기 위함</li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/02/15 3:43 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@TestConfiguration
public class JPATestConfig {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}
