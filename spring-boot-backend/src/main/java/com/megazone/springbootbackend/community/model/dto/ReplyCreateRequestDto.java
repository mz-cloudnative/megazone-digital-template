package com.megazone.springbootbackend.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateRequestDto {
    private String content;
    private Long articleId;
    private Long userId;
    private Long parentId;
}
