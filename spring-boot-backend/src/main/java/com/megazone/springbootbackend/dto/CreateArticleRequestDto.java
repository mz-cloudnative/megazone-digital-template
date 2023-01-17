package com.megazone.springbootbackend.dto;

import lombok.Getter;

@Getter
public class CreateArticleRequestDto {
    private String title;
    private String content;
    private int boardId;
}
