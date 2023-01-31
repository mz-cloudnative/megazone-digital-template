package com.megazone.springbootbackend.communityAPI.service;

import com.megazone.springbootbackend.communityAPI.model.dto.ReplyCreateRequestDto;
import com.megazone.springbootbackend.communityAPI.model.dto.ReplyDto;
import com.megazone.springbootbackend.communityAPI.model.entity.Reply;
import com.megazone.springbootbackend.communityAPI.repository.ArticleRepository;
import com.megazone.springbootbackend.communityAPI.repository.ReplyRepository;
import com.megazone.springbootbackend.communityAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    List<ReplyDto> list2;

    public List<Reply> findReplyByArticleId(Long articleId){
        List<Reply> list=replyRepository.findReplyByArticleId(articleId);

//        for(Reply i : list){
//            list2.add(ReplyDto.of(i));
//        }
        return list;
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


}
