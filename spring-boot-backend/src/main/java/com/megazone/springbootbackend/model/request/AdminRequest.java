package com.megazone.springbootbackend.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminRequest {
    private String id;
    private String password;
    private String name;
    private String birth;
}
