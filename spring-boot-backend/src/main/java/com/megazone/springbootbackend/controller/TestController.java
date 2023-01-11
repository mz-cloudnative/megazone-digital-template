package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.TestUser;
import com.megazone.springbootbackend.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/user")
    public List<TestUser> selectTestName(){
        final var userList = repository.findAll();
        return userList;
    }

    @GetMapping("/one-user")
    public Optional<TestUser> selectOneTestUser(Long id){
        final var oneUser = repository.findById(id);
        return oneUser;
    }

}
