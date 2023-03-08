package com.megazone.springbootbackend.community.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEvent;

public class CommentCreatedEvent extends ApplicationEvent {
    private Long articleId;
    private Long replyId;

    private String replyContent;

    private String articleAuthor;

    private String articleTitle;

    private String replyAuthor;

    private String parentReplyAuthor;



    public CommentCreatedEvent(Object source, Long articleId, Long replyId, String articleAuthor, String replyContent, String articleTitle) {
        super(source);
        this.articleId = articleId;
        this.replyId = replyId;
        this.articleAuthor = articleAuthor;
        this.replyContent = replyContent;
        this.articleTitle = articleTitle;
    }

    public CommentCreatedEvent(Object source, Long replyId, String replyContent, String replyAuthor, String parentReplyAuthor) {
        super(source);
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyAuthor = replyAuthor;
        this.parentReplyAuthor = parentReplyAuthor;
    }


    public Long getarticleId() {
        return articleId;
    }

    public Long getreplyId() {
        return replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getReplyAuthor() {
        return replyAuthor;
    }

    public String getParentReplyAuthor() {
        return parentReplyAuthor;
    }
}