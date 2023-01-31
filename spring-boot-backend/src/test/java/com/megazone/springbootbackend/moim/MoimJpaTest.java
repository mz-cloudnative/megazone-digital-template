package com.megazone.springbootbackend.moim;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.dto.MoimDetailResponse;
import com.megazone.springbootbackend.moim.model.dto.MoimResponse;
import com.megazone.springbootbackend.moim.model.entity.MoimDetailEntity;
import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import com.megazone.springbootbackend.moim.repository.MoimDetailRepository;
import com.megazone.springbootbackend.moim.repository.MoimRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("JPA 테스트")
public class MoimJpaTest {

  @Autowired
  private MoimRepository moimRepository;

  @Autowired
  private MoimDetailRepository moimDetailRepository;

  @Test
  @DisplayName("모임 정보 저장")
  public void moim_info_save(){
      // given
      MoimEntity moim = MoimEntity.builder().moimName("모임을 시작해볼까? 1").moimStatus(Status.RESERVE).build();

      // when
      MoimEntity saveMoim = moimRepository.save(moim);
      MoimDetailEntity moimDetail = MoimDetailEntity.builder().moimDetailContent("모임 세부내용입니다.").moimDetailPlace("모임 장소는 요기에요!!!!").moimDetailableNum(5).moimEntity(saveMoim).build();
      MoimDetailEntity saveMoimDetail = moimDetailRepository.save(moimDetail);

      // then
      MoimResponse.fromEntity(saveMoim);
      MoimDetailResponse.fromEntity(saveMoimDetail);
  }

  @Test
  @DisplayName("불러온 모임 정보를 변경하기")
  public void moim_findId_update(){
      //given
      var findMoimInfo = moimRepository.findById(2L).orElseThrow(NullPointerException::new);
      var findMoimDetail = moimDetailRepository.findById(findMoimInfo.getMoimId()).orElseThrow(NullPointerException::new);

      //when
      findMoimInfo.setMoimName("모임은 바로바로~!!");
      MoimEntity updateMoimInfo = moimRepository.save(findMoimInfo);
      MoimResponse moimResponse = MoimResponse.fromEntity(updateMoimInfo);

      findMoimDetail.setMoimDetailContent("모임 장소가 변경되었습니다. 확인해주세요! 어서");
      MoimDetailEntity updateMoimDetail = moimDetailRepository.save(findMoimDetail);
      MoimDetailResponse moimDetailResponse = MoimDetailResponse.fromEntity(updateMoimDetail);

      //then
      System.out.println("moimResponse = " + moimResponse.toString());
      System.out.println("moimDetailResponse = " + moimDetailResponse.toString());
  }
}
