package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.dto.PageResponseDto;
import com.megazone.springbootbackend.model.Article;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static com.megazone.springbootbackend.model.QArticle.article;

@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public Page<PageResponseDto> searchAll(Pageable pageable) {
        List<Article> content = queryFactory
                .selectFrom(article)
                .orderBy(article.articleId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PageResponseDto> pages = content
                .stream()
                .map(PageResponseDto::of)
                .collect(Collectors.toList());

        int totalSize = queryFactory
                .selectFrom(article)
                .fetch()
                .size();

        return new PageImpl<>(pages, pageable, totalSize);
    }

    public Page<PageResponseDto> searchByBoardId(Pageable pageable, int boardId) {
        List<Article> content = queryFactory
                .selectFrom(article)
                .where(article.boardId.eq(boardId))
                .orderBy(article.articleId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PageResponseDto> pages = content
                .stream()
                .map(PageResponseDto::of)
                .collect(Collectors.toList());

        int totalSize = queryFactory
                .selectFrom(article)
                .fetch()
                .size();

        return new PageImpl<>(pages, pageable, totalSize);
    }

}
