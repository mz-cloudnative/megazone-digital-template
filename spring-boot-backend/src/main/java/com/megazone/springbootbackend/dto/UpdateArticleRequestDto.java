package com.megazone.springbootbackend.dto;

import lombok.Getter;

@Getter
public class UpdateArticleRequestDto {
    private Long id;
    private String title;
    private String content;
}
