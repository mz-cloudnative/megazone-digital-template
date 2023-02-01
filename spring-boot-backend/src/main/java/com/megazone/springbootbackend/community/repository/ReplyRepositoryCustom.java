package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Reply;

import java.util.List;

public interface ReplyRepositoryCustom {
    List<Reply> findReplyByArticleId(Long articleId);
}
