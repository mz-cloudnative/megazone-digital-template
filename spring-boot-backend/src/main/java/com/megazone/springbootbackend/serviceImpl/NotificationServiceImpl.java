package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public SseEmitter alarm(String userId, String eventId) {
        String id = userId + "_" + System.currentTimeMillis();

        return null;
    }
}
