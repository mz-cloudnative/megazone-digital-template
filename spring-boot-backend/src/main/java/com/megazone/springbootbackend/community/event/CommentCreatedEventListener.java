package com.megazone.springbootbackend.community.event;

import com.megazone.springbootbackend.community.model.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class CommentCreatedEventListener {
    private final Executor executor;

    @Autowired
    public CommentCreatedEventListener(@Qualifier("taskExecutor") Executor executor) {
        this.executor = executor;
    }

    @EventListener
    @Async
    public void handleCommentCreatedEvent(Notification noti) {
        executor.execute(() -> {
            // 알림 생성 및 전송 코드
        });
    }
}