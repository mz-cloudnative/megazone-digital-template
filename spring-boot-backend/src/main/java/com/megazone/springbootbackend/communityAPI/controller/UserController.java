package com.megazone.springbootbackend.communityAPI.controller;

import com.megazone.springbootbackend.communityAPI.model.dto.UserDto;
import com.megazone.springbootbackend.communityAPI.model.entity.Users;
import com.megazone.springbootbackend.communityAPI.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//@Tag(name = "사용자", description = "사용자 관련 api 입니다.")
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    //private final UserRepository repository;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @Operation(summary = "로그인 메서드", description = "로그인 메서드입니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserDto.class))),
//            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = UserDto.class)))
//    })
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
