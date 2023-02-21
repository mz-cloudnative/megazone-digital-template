package com.megazone.springbootbackend.event;

import com.megazone.springbootbackend.model.dto.AdminDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegisteredEvent {
    private AdminDto adminDto;
}
