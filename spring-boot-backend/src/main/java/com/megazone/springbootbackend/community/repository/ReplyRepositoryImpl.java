package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Reply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.megazone.springbootbackend.community.model.entity.QReply.reply;

import java.util.List;

@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reply> findReplyByArticleId(Long articleId){
        return queryFactory.selectFrom(reply)
                .leftJoin(reply.parent)
                .fetchJoin()
                .where(reply.article.articleId.eq(articleId))
                .orderBy(
                        reply.parent.replyId.asc().nullsFirst(),
                        reply.replyId.asc()
                ).fetch();
    }

}
