package com.megazone.springbootbackend.moim.service;

import com.megazone.springbootbackend.moim.model.domain.Status;
import com.megazone.springbootbackend.moim.model.dto.MoimResponse;
import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import com.megazone.springbootbackend.moim.repository.MoimRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoimService {

  private final MoimRepository moimRepository;

  public MoimResponse createMoimTicket(String moimName, String administrator) {
    // 모임 정보 생성
    MoimEntity moim = MoimEntity.builder().moimAdministrator(administrator).moimName(moimName).moimStatus(Status.CREATE).build();
    MoimEntity saveMoim = moimRepository.save(moim);
    return MoimResponse.fromEntity(saveMoim);
  }

  public MoimResponse findMoimInfo(Long id){
    MoimEntity moimInfo = moimRepository.findById(id).orElseThrow(NullPointerException::new);
    return MoimResponse.fromEntity(moimInfo);
  }

  public void sendToEmail(String moimName, String administrator) {
    System.out.println(String.format("send Email~~ 모임 이름[%s], 개설자[%s]", moimName, administrator));
  }

}
