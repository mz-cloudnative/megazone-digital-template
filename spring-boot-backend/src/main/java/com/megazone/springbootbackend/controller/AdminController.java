package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.dto.AdminDto;
import com.megazone.springbootbackend.model.request.AdminRequest;
import com.megazone.springbootbackend.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    public void register(@RequestBody AdminRequest request) {
        AdminDto dto = AdminDto.builder()
                .id(request.getId())
                .password(request.getPassword())
                .name(request.getName())
                .birth(request.getBirth())
                .build();
        if (adminService.checkId(dto.getId())) {
            adminService.register(dto);
        }
    }
}
