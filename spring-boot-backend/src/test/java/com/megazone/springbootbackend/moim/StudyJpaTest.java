package com.megazone.springbootbackend.moim;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.dto.MoimStudyRoomDetailResponse;
import com.megazone.springbootbackend.moim.model.dto.MoimStudyRoomResponse;
import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomDetailEntity;
import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomEntity;
import com.megazone.springbootbackend.moim.repository.MoimStudyRoomDetailRepository;
import com.megazone.springbootbackend.moim.repository.MoimStudyRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("JPA 테스트 - 스터디룸")
public class StudyJpaTest {

  @Autowired
  private MoimStudyRoomRepository moimStudyRoomRepository;

  @Autowired
  private MoimStudyRoomDetailRepository moimStudyRoomDetailRepository;

  @Test
  @DisplayName("스터디룸 정보 저장하기")
  public void moim_study_room_save(){

    //given
    MoimStudyRoomEntity moimStudyRoomEntity = MoimStudyRoomEntity.builder().roomName("햇님 스터디룸").roomStatus(Status.NONE).build();

    //when
    MoimStudyRoomEntity saveMoimStudyRoom = moimStudyRoomRepository.save(moimStudyRoomEntity);
    MoimStudyRoomDetailEntity moimStudyRoomDetail = MoimStudyRoomDetailEntity.builder().roomDetailContent("햇님 스터디룸에 오신걸 환영합니다.").moimStudyRoomEntity(saveMoimStudyRoom).build();
    MoimStudyRoomDetailEntity saveMoimStudyRoomDetail = moimStudyRoomDetailRepository.save(moimStudyRoomDetail);

    //then
    MoimStudyRoomResponse.fromEntity(saveMoimStudyRoom);
    MoimStudyRoomDetailResponse.fromEntity(saveMoimStudyRoomDetail);
  }

  @Test
  @DisplayName("저장된 스터디룸 정보를 변경하기")
  public void moim_findId_update(){

    //given
    var findMoimStudyRoomInfo = moimStudyRoomRepository.findById(1L).orElseThrow(NullPointerException::new);
    var findMoimStudyRoomDetail = moimStudyRoomDetailRepository.findById(findMoimStudyRoomInfo.getRoomId()).orElseThrow(NullPointerException::new);

    //when
    findMoimStudyRoomInfo.setRoomName("달님 스터디룸1");
    findMoimStudyRoomInfo.setRoomStatus(Status.RESERVE);
    MoimStudyRoomEntity updateMoimStudyRoomInfo = moimStudyRoomRepository.save(findMoimStudyRoomInfo);
    MoimStudyRoomResponse moimStudyRoomResponse = MoimStudyRoomResponse.fromEntity(updateMoimStudyRoomInfo);

    findMoimStudyRoomDetail.setRoomDetailContent("스터디 룸 명이 변경되었습니다.1");
    MoimStudyRoomDetailEntity updateMoimStudyRoomDetail = moimStudyRoomDetailRepository.save(findMoimStudyRoomDetail);
    MoimStudyRoomDetailResponse moimStudyRoomDetailResponse = MoimStudyRoomDetailResponse.fromEntity(updateMoimStudyRoomDetail);

    //then
    System.out.println("moimStudyRoomResponse = " + moimStudyRoomResponse.toString());
    System.out.println("moimStudyRoomDetailResponse = " + moimStudyRoomDetailResponse.toString());
  }

  @Test
  @DisplayName("스터디룸 세부정보를 추가하기")
  public void moim_findId_study_room_detail_add(){
    //given
    var findMoimStudyRoomInfo = moimStudyRoomRepository.findById(1L).orElseThrow(NullPointerException::new);
    //when
    MoimStudyRoomDetailEntity moimStudyRoomDetailEntity = MoimStudyRoomDetailEntity.builder().moimStudyRoomEntity(findMoimStudyRoomInfo).roomAbleNum(3).roomDetailContent("달님 스터디룸에 특별한 이슈가 발생했습니다. ").build();
    //then
    MoimStudyRoomDetailEntity result = moimStudyRoomDetailRepository.save(moimStudyRoomDetailEntity);
  }

  @Test
  @Transactional(readOnly = true)
  @DisplayName("스터디룸 정보를 조회하기")
  public void moim_findId_study_room_select(){
    //given
    var findMoimStudyRoomInfo = moimStudyRoomRepository.findById(1L).orElseThrow(NullPointerException::new);
    //when
    MoimStudyRoomResponse response = MoimStudyRoomResponse.fromEntity(findMoimStudyRoomInfo);
    //then
    System.out.println("response.toString() = " + response.toString());
  }
}
