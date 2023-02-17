package com.megazone.springbootbackend.community.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Data
public class PostViewCountDto {

    @NotNull
    private Long Id;
}
