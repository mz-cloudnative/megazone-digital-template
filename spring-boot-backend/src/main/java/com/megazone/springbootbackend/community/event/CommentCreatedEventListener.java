package com.megazone.springbootbackend.community.event;

import com.megazone.springbootbackend.community.model.entity.Notification;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class CommentCreatedEventListener {
    private final Executor executor;

    private final String tokenKey = "";

    @Autowired
    public CommentCreatedEventListener(@Qualifier("taskExecutor") Executor executor) {
        this.executor = executor;
    }

    @EventListener
    @Async
    public void handleCommentCreatedEvent(CommentCreatedEvent event) {
        System.out.println("댓글 달리면 여기로 오나요?");
        executor.execute(() -> {
            // 알림 생성 및 전송 코드
            //이제 여기에 슬랙 알람 연동할 것임!
            SlackApi slackApi = new SlackApi("https://hooks.slack.com/services/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");
            slackApi.call(new SlackMessage("댓글 알람: "+event.getArticleAuthor()+"님, "+"'"+event.getArticleTitle()+"'"+" 게시글에 "+"'"+event.getReplyContent()+"'"+" 내용의 댓글이 달렸습니다."));
//            if(event.getParentReplyAuthor()!=null){
//                SlackApi.call(new SlackMessage("대댓글 알람:"));
//            }
        });
    }
}