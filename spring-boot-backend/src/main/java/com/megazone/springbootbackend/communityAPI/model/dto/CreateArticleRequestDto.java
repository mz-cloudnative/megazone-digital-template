package com.megazone.springbootbackend.communityAPI.model.dto;

import lombok.Getter;

@Getter
public class CreateArticleRequestDto {
    private String title;
    private String content;
    private int boardId;
}
