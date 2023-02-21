package com.megazone.springbootbackend.listener;

import com.megazone.springbootbackend.model.dto.AdminDto;
import com.megazone.springbootbackend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminEventListener {
    private final AdminRepository adminRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void countAdmin(AdminDto dto) throws InterruptedException {
        log.info("ID: " + dto.getId());
        log.info("Password: " + dto.getPassword());
        log.info("Name: " + dto.getName());
        log.info("Birth: " + dto.getBirth());
        log.info("현재 관리자는 총 " + adminRepository.count() + "명입니다.");
    }
}
