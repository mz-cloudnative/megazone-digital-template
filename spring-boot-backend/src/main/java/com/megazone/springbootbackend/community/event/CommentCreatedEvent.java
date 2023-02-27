package com.megazone.springbootbackend.community.event;

import org.springframework.context.ApplicationEvent;

public class CommentCreatedEvent extends ApplicationEvent {
    private Long articleId;
    private Long replyId;


    public CommentCreatedEvent(Object source, Long articleId, Long replyId) {
        super(source);
        this.articleId = articleId;
        this.replyId = replyId;
    }

    public Long getarticleId() {
        return articleId;
    }

    public Long getreplyId() {
        return replyId;
    }
}