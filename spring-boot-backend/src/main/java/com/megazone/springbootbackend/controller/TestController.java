package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.TestUser;
import com.megazone.springbootbackend.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestRepository repository;

    @GetMapping("/")
    public String getTestName() {
        TestUser testUser = new TestUser(1L, "ff");
        repository.save(testUser);
        return testUser.getName();
    }
}
