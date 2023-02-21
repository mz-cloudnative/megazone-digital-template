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
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("JPA 테스트 - 모임")
public class MoimJpaTest {

  @Autowired
  private MoimRepository moimRepository;

  @Autowired
  private MoimDetailRepository moimDetailRepository;

  @Test
  @DisplayName("모임 정보 저장하기")
  public void moim_info_save(){
    // given
    MoimEntity moim = MoimEntity.builder().moimAdministrator("무지개").moimName("모임을 시작해볼까?").moimStatus(Status.CREATE).build();

    // when
    MoimEntity saveMoim = moimRepository.save(moim);
    MoimDetailEntity moimDetail = MoimDetailEntity.builder().moimDetailContent("모임 세부내용입니다.").moimDetailPlace("모임 장소는 요기에요!!!!").moimDetailableNum(5).moimEntity(saveMoim).build();
    MoimDetailEntity saveMoimDetail = moimDetailRepository.save(moimDetail);

    // then
    MoimResponse.fromEntity(saveMoim);
    MoimDetailResponse.fromEntity(saveMoimDetail);
  }

  @Test
  @DisplayName("저장된 모임 정보를 변경하기")
  public void moim_findId_update(){
    //given
    var findMoimInfo = moimRepository.findById(1L).orElseThrow(NullPointerException::new);
    var findMoimDetail = moimDetailRepository.findById(4L).orElseThrow(NullPointerException::new);

    //when
//    findMoimInfo.setMoimName("햇님모임1");
//    MoimEntity updateMoimInfo = moimRepository.save(findMoimInfo);
    MoimResponse moimResponse = MoimResponse.fromEntity(findMoimInfo);

    findMoimDetail.setMoimDetailContent("햇님 모임 장소가 변경되었습니다. 확인해주세요! 어서!!");
    MoimDetailEntity updateMoimDetail = moimDetailRepository.save(findMoimDetail);
    MoimDetailResponse moimDetailResponse = MoimDetailResponse.fromEntity(updateMoimDetail);

    //then
    System.out.println("moimResponse = " + moimResponse.toString());
    System.out.println("moimDetailResponse = " + moimDetailResponse.toString());
  }

  @Test
  @DisplayName("모임 정보를 추가하기")
  public void moim_findId_detail_add(){
    //given
    var findMoimInfo = moimRepository.findById(1L).orElseThrow(NullPointerException::new);
    MoimDetailEntity addMoimDetail = MoimDetailEntity.builder().moimDetailableNum(3).moimDetailContent("햇님 모임 정보 추가요~").moimDetailPlace("햇님 모임장소 1").moimEntity(findMoimInfo).build();

    //when
    moimDetailRepository.save(addMoimDetail);
    //then
  }

  @Test
  @Transactional(readOnly = true)
  @DisplayName("모임 정보를 조회하기")
  public void moim_findId_select(){
    //given
    var findMoimInfo = moimRepository.findById(1L).orElseThrow(NullPointerException::new);
    //when
    MoimResponse moimResponse = MoimResponse.fromEntity(findMoimInfo);
    //then
    System.out.println("moimResponse.toString() = " + moimResponse.toString());

  }
}
