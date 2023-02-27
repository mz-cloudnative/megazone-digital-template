package com.megazone.springbootbackend.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter alarm(String id, String eventId);
}
