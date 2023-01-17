package com.megazone.springbootbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.megazone.springbootbackend.model.Users;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    public static UserDto of(Users user){
        return UserDto.builder().username(user.getUsername())
                .nickname(user.getNickname()).build();
    }
}