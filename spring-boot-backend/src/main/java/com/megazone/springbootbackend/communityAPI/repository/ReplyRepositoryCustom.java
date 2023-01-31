package com.megazone.springbootbackend.communityAPI.repository;

import com.megazone.springbootbackend.communityAPI.model.dto.ReplyDto;
import com.megazone.springbootbackend.communityAPI.model.entity.Reply;

import java.util.List;

public interface ReplyRepositoryCustom {
    List<Reply> findReplyByArticleId(Long articleId);
}
