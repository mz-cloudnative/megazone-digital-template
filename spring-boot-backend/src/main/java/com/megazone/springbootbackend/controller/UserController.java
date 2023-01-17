package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.dto.UserDto;
import com.megazone.springbootbackend.model.Users;
import com.megazone.springbootbackend.repository.UserRepository;
import com.megazone.springbootbackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Tag(name = "인증", description = "인증 관련 api 입니다.")
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    //private final UserRepository repository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<UserDto> getMyUserInfo() {
        UserDto myInfoBySecurity = userService.getMyInfoBySecurity();
        //System.out.println(myInfoBySecurity.toString());
        //여기 안오는거 확인
        return ResponseEntity.ok((myInfoBySecurity));
    }

//    @GetMapping("/")
//    public String getTestName() {
//        Users user = new Users("ff");
//        repository.save(user);
//        return user.getUsername();
//    }

//    @GetMapping("/user")
//    public List<TestUser> selectTestName(){
//        final var userList = repository.findAll();
//        return userList;
//    }
//
//    @GetMapping("/one-user")
//    public Optional<TestUser> selectOneTestUser(Long id){
//        final var oneUser = repository.findById(id);
//        return oneUser;
//    }

}
