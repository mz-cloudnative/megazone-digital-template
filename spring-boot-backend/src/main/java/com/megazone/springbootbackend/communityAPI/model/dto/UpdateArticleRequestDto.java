package com.megazone.springbootbackend.communityAPI.model.dto;

import lombok.Getter;

@Getter
public class UpdateArticleRequestDto {
    private Long id;
    private String title;
    private String content;
}
