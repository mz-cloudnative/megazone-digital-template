package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.event.CommentCreatedEvent;
import com.megazone.springbootbackend.community.model.dto.ReplyCreateRequestDto;
import com.megazone.springbootbackend.community.model.dto.ReplyDto;
import com.megazone.springbootbackend.community.model.entity.Notification;
import com.megazone.springbootbackend.community.model.entity.Reply;
import com.megazone.springbootbackend.community.repository.ArticleRepository;
import com.megazone.springbootbackend.community.repository.ReplyRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ApplicationEventPublisher eventPublisher;


    public List<ReplyDto> findReplyByArticleId(Long articleId){
        List<Reply> list=replyRepository.findReplyByArticleId(articleId);
        return convertNestedStructure(list);
    }

    public void createReply(ReplyCreateRequestDto requestDto){
        Reply reply = replyRepository.save(
                Reply.createReply(requestDto.getContent(),
                        articleRepository.findById(requestDto.getArticleId()).orElseThrow(NullPointerException::new),
                        userRepository.findById(requestDto.getUserId()).orElseThrow(NullPointerException::new),
                        requestDto.getParentId() != null ?
                        replyRepository.findById(requestDto.getParentId()).orElseThrow(NullPointerException::new):null)
        );
        String articleAuthor = articleRepository.findById(requestDto.getArticleId()).get().getUsers().getNickname();
        String articleTitle = articleRepository.findById(requestDto.getArticleId()).get().getTitle();
        String replyAuthor = replyRepository.findById(reply.getReplyId()).get().getUsers().getNickname();
        if(reply.getParent()!=null){
            String parentReplyAuthor = reply.getParent().getUsers().getNickname();
        }


        //자기가 쓴 게시글에 자기가 댓글이든 대댓글이든 달면 알림이 안 가도록 처리.
        if(articleAuthor!=replyAuthor) {
            if(reply.getParent()!=null){ //지금 쓴 댓글이 대댓글이면 글 작성자에게 새댓글 알림과 부모댓글 작성자에게 대댓글 알림을 동시에 보냄.
                if(reply.getParent().getUsers().getNickname()!=reply.getUsers().getNickname()){ //또 대댓글인데, 부모댓글 작성자가 지금 대댓글 작성자랑 똑같다면 대댓글 알림은 안 가도록 처리
                    CommentCreatedEvent event = new CommentCreatedEvent(this, requestDto.getArticleId(), requestDto.getUserId(), articleAuthor, requestDto.getContent(), articleTitle);
                    eventPublisher.publishEvent(event);
                }else{
                    CommentCreatedEvent event = new CommentCreatedEvent(this, requestDto.getArticleId(), requestDto.getUserId(), articleAuthor, requestDto.getContent(), articleTitle);
                    CommentCreatedEvent rereplyEvent = new CommentCreatedEvent(this, reply.getReplyId(), requestDto.getContent(), replyAuthor, event.getParentReplyAuthor());
                    eventPublisher.publishEvent(event);
                    eventPublisher.publishEvent(rereplyEvent);

                }
            }else { //지금 쓴 댓글이 대댓글이 아니면 그냥 글 작성자에게 새댓글 알림만을 보냄.
                CommentCreatedEvent event = new CommentCreatedEvent(this, requestDto.getArticleId(), requestDto.getUserId(), articleAuthor, requestDto.getContent(), articleTitle);
                eventPublisher.publishEvent(event);
            }
        }
    }

    private List<ReplyDto> convertNestedStructure(List<Reply> replies){
        List<ReplyDto> result = new ArrayList<>();
        Map<Long, ReplyDto> map = new HashMap<>();
        replies.stream().forEach(r -> {
            ReplyDto dto = ReplyDto.toDto(r);
            map.put(dto.getReplyId(), dto);
            if(r.getParent()!=null)
                map.get(r.getParent().getReplyId()).getChildren().add(dto);
            else
                result.add(dto);
        });
        return result;
    }

}
