package com.megazone.springbootbackend.community.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;
    private Long tokenExpiresIn;
}
