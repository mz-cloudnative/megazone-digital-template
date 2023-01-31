package com.megazone.springbootbackend.moim;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.dto.MoimResponse;
import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import com.megazone.springbootbackend.moim.repository.MoimRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("JPA 테스트")
public class MoimJpaTest {

  @Autowired
  private MoimRepository moimRepository;

  @Order(1)
  @Test
  @DisplayName("모임 정보 저장")
  public void moim_info_save(){
      // given
      MoimEntity moim = MoimEntity.builder().moimId(2L).moimName("모임을 시작해볼까? ").moimStatus(Status.RESERVE).build();
      // when
      MoimEntity saveMoim = moimRepository.save(moim);
      // then
      System.out.println("saveMoim = " + MoimResponse.fromEntity(saveMoim));
  }

  @Order(2)
  @Test
  @DisplayName("모임 정보 불러오기")
  public void moim_findId(){
      //given
      var findEntity = moimRepository.findById(2L).orElseThrow(NullPointerException::new);
      //when
      MoimResponse moimResponse = MoimResponse.fromEntity(findEntity);
      //then
      System.out.println("moimResponse = " + moimResponse);
  }
  @Order(3)
  @Test
  @DisplayName("불러온 모임 정보를 변경하기")
  public void moim_findId_update(){
      //given
      var findEntity = moimRepository.findById(2L).orElseThrow(NullPointerException::new);

      //when
      findEntity.setMoimName("모임은 바로바로~");
      moimRepository.save(findEntity);
      MoimResponse moimResponse = MoimResponse.fromEntity(findEntity);

      //then
      System.out.println("moimResponse = " + moimResponse);
  }
}
