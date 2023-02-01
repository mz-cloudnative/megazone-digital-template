package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.model.dto.UserDto;
import com.megazone.springbootbackend.community.jwt.TokenProvider;
import com.megazone.springbootbackend.community.model.entity.Authority;
import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.model.entity.Users;
import com.megazone.springbootbackend.community.repository.TokenRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import com.megazone.springbootbackend.community.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private TokenRepository tokenRepository;
    private TokenProvider tokenProvider;
    private final SecurityUtil securityUtil;

    @Transactional
    public Users signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Users user = Users.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public void logout(){
        SecurityContextHolder.clearContext();
        System.out.println("로그아웃 성공");
    }

    @Transactional
    public Users findByTokens(Token token){
        return userRepository.findByTokens(token);
    }

    @Transactional
    public Users findByUsername(String username){
        return userRepository.findByUsername(username).get();
    }


    @Transactional(readOnly = true)
    public Optional<Users> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Users> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }


    public UserDto getMyInfoBySecurity(){
        return userRepository.findById(securityUtil.getCurrentUserId()).map(UserDto::of).orElseThrow(()->new RuntimeException("로그인 유저 정보가 없습니다"));
    }
}