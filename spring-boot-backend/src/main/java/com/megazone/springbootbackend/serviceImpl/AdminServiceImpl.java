package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.model.dto.AdminDto;
import com.megazone.springbootbackend.model.entity.AdminEntity;
import com.megazone.springbootbackend.repository.AdminRepository;
import com.megazone.springbootbackend.service.AdminService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.megazone.springbootbackend.model.entity.QAdminEntity.adminEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final AdminRepository adminRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void register(AdminDto adminDto) {
        AdminEntity entity = AdminEntity.builder()
                .id(adminDto.getId())
                .password(adminDto.getPassword())
                .name(adminDto.getName())
                .birth(adminDto.getBirth())
                .build();
        applicationEventPublisher.publishEvent(adminDto);
        adminRepository.save(entity);
    }

    @Override
    public boolean checkId(String id) {
        List<String> list = jpaQueryFactory.select(adminEntity.id).from(adminEntity).where(adminEntity.id.eq(id)).fetch();
        return list.isEmpty();
    }
}
