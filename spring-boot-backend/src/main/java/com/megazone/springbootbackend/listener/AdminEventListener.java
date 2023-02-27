package com.megazone.springbootbackend.listener;

import com.megazone.springbootbackend.model.dto.AdminDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class AdminEventListener {
    @Async
    @TransactionalEventListener
    public void registerAdmin(AdminDto adminDto) {
        log.info("name: " + adminDto.getName());
        log.info("완료");
    }
}
