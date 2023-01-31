package com.megazone.springbootbackend.communityAPI.model.dto;

import com.megazone.springbootbackend.communityAPI.model.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDto {
    private Long replyId;
    private String content;
    private Long userId;
    private String nickname;
    private List<ReplyDto> children = new ArrayList<>();

    public ReplyDto(Long replyId, String content, Long userId, String nickname) {
        this.replyId = replyId;
        this.content = content;
        this.userId = userId;
        this.nickname = nickname;
    }

    public static ReplyDto of(Reply reply){
        return ReplyDto.builder().replyId(reply.getReplyId())
                .content(reply.getContent())
                .userId(reply.getUsers().getUserId())
                .nickname(reply.getUsers().getNickname()).build();
    }

}
