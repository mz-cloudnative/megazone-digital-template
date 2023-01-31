package com.megazone.springbootbackend.communityAPI.model.dto;

import com.megazone.springbootbackend.communityAPI.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponseDto {
    private Long articleId;
    private String username;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private boolean isWritten;
    private int boardId;


    public static ArticleResponseDto of(Article article, boolean bool) {
        return ArticleResponseDto.builder()
                .articleId(article.getArticleId())
                .username(article.getUsers().getNickname())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .updatedAt(article.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isWritten(bool)
                .boardId(article.getBoardId())
                .build();
    }

}

