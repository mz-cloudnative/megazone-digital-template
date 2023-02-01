package com.megazone.springbootbackend.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.beans.ConstructorProperties;

@Builder
@Data
@AllArgsConstructor
public class SearchDto {

    private String nickname;

    private String title;

    private String content;
}
