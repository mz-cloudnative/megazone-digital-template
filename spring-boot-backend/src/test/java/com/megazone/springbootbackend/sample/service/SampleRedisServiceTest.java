package com.megazone.springbootbackend.sample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.megazone.springbootbackend.sample.model.domain.SampleRedis;
import com.megazone.springbootbackend.sample.repository.SampleRedisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

/***************************************************
 * <ul>
 * <li>업무 그룹명 : </li>
 * <li>서브 업무명 : </li>
 * <li>파  일  명 : SampleServiceTest</li>
 * <li>작  성  자 : mz01-ohyunbk</li>
 * <li>작  성  일 : 2023/01/19</li>
 * <li>설     명 : </li>
 * </ul>
 * <pre>
 * ======================================
 * 작성자             일시                  내용
 * mz01-ohyunbk    2023/01/19 5:26 PM    최초 생성
 * ======================================
 * </pre>
 ***************************************************/
@DataRedisTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class) //@Order에 의해 메소드별 실행순서 보장
@DisplayName("Redis JPA 테스트")
class SampleRedisServiceTest {

  @Autowired
  private SampleRedisRepository sampleRedisRepository;

  SampleRedis sampleDataRequest;

  final static String SAMPLE_NAME = "test99";
  final static Long SAMPLE_ID = 99L;

  @BeforeEach
  public void givenInit() {
    sampleDataRequest = SampleRedis.builder()
        .sampleId(SAMPLE_ID)
        .sampleName(SAMPLE_NAME)
        .expiration(30L)
        .build();
  }
  public SampleRedis getRedisSample(String name) {
    return sampleRedisRepository.findBySampleName(name)
        .orElseThrow(() -> new NullPointerException("존재하지 않습니다."));
  }

  @Test
  @Order(value = 1) //테스트 실행 순서
  @DisplayName("조회시 유효시간 만료로 실패")
  void save_and_find_ttl_over() {
    //given

    //when
    try {
      sampleRedisRepository.save(sampleDataRequest);
      Thread.sleep(1000 * 30);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    //then
    assertThrows(NullPointerException.class,() -> getRedisSample(SAMPLE_NAME));
  }


  @Test
  @Order(value = 2) //테스트 실행 순서
  @DisplayName("저장 및 조회 성공")
  void save_and_find_success() {
    //given
    sampleDataRequest.setExpiration(90L);

    //when
    sampleRedisRepository.save(sampleDataRequest);
    var result = getRedisSample(SAMPLE_NAME);

    //then
    assertEquals(SAMPLE_ID, result.getSampleId());
  }

  @Test
  @Order(value = 3) //테스트 실행 순서
  @DisplayName("삭제 성공")
  void delete_success() {
    //given

    //when
    var result = getRedisSample(SAMPLE_NAME);
    sampleRedisRepository.delete(result);

    //then
    assertThrows(NullPointerException.class, () -> getRedisSample(SAMPLE_NAME));
  }
}