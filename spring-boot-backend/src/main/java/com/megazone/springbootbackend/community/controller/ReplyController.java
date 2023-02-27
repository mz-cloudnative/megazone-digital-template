package com.megazone.springbootbackend.community.controller;

import com.megazone.springbootbackend.community.event.CommentCreatedEvent;
import com.megazone.springbootbackend.community.model.dto.ReplyCreateRequestDto;
import com.megazone.springbootbackend.community.model.dto.ReplyDto;
import com.megazone.springbootbackend.community.model.entity.Notification;
import com.megazone.springbootbackend.community.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    private final ApplicationEventPublisher eventPublisher;

    @GetMapping(value = "/replies/{articleId}")
    public List<ReplyDto> findAllRepliesByArticleId(@PathVariable("articleId") Long articleId) {
        return replyService.findReplyByArticleId(articleId);
    }

    @PostMapping(value = "/replies")
    public void createReply(@RequestBody ReplyCreateRequestDto requestDto) {
        replyService.createReply(requestDto);

        CommentCreatedEvent event = new CommentCreatedEvent(this, requestDto.getArticleId(), requestDto.getUserId());
        eventPublisher.publishEvent(event);
    }
}
