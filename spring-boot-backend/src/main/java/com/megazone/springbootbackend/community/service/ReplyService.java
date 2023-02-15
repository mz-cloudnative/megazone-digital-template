package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.model.dto.ReplyCreateRequestDto;
import com.megazone.springbootbackend.community.model.dto.ReplyDto;
import com.megazone.springbootbackend.community.model.entity.Reply;
import com.megazone.springbootbackend.community.repository.ArticleRepository;
import com.megazone.springbootbackend.community.repository.ReplyRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
