package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.model.dto.AdminDto;
import com.megazone.springbootbackend.model.entity.AdminEntity;
import com.megazone.springbootbackend.repository.AdminRepository;
import com.megazone.springbootbackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AdminRepository adminRepository;

    @Override
    @Transactional
    public void register(AdminDto adminDto) {
        AdminEntity entity = AdminEntity.builder()
                .id(adminDto.getId())
                .password(adminDto.getPassword())
                .name(adminDto.getName())
                .birth(adminDto.getBirth())
                .build();
        adminRepository.save(entity);
        log.info(adminDto.getName() + " 님이 회원가입을 하였습니다.");
        applicationEventPublisher.publishEvent(adminDto);
    }
}
