package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.dto.PageResponseDto;
import com.megazone.springbootbackend.community.model.dto.SearchDto;
import com.megazone.springbootbackend.community.model.entity.Article;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.megazone.springbootbackend.community.model.entity.QArticle.article;

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

    @Override
    public Page<PageResponseDto> searchWithPaging(Pageable pageable, SearchDto searchDto) {

        BooleanBuilder builder = new BooleanBuilder();
        if (searchDto.getNickname()!=null && searchDto.getNickname()!="") {
            builder.or(article.users.nickname.contains(searchDto.getNickname()));
        }
        if (searchDto.getTitle()!=null && searchDto.getTitle()!="") {
            builder.or(article.title.contains(searchDto.getTitle()));
        }
        if (searchDto.getContent()!=null && searchDto.getContent()!="") {
            builder.or(article.content.contains(searchDto.getContent()));
        }

        List<Article> articles = queryFactory
                .selectFrom(article)
                .where(builder)
                .orderBy(article.articleId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<PageResponseDto> pages = articles
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
