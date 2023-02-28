package com.megazone.springbootbackend.community.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.megazone.springbootbackend.community.model.dto.LoginDto;
import com.megazone.springbootbackend.community.model.dto.TokenDto;
import com.megazone.springbootbackend.community.jwt.JwtFilter;
import com.megazone.springbootbackend.community.jwt.TokenProvider;
import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.repository.TokenRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import com.megazone.springbootbackend.community.service.AuthService;
import com.megazone.springbootbackend.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.*;
import java.util.Date;


@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Value("${jwt.token-validity-in-seconds}")
    long tokenValidityInSeconds;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto jwt = tokenProvider.createToken(authentication);

        Date now = new Date();

        LocalTime localTime = LocalTime.now().plusSeconds(tokenValidityInSeconds);
        LocalDateTime localDateTime = localTime.atDate(LocalDate.now());
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        //System.out.println(userRepository.findByUsername(authentication.getName()).get()==null);

        Token token = Token.builder()
                .users(userRepository.findByUsername(authentication.getName()).get())
                .tokenName(jwt.getToken())
                .createdDate(now)
                .expiredDate(date)
                .build();

        tokenRepository.save(token);

        logger.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+authenticationToken.getAuthorities().toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        logger.info("로그인을 하는 나는 여기로 옵니다.");

        //authService.deleteExpiredToken(token.getUsers());

        return new ResponseEntity<>(new TokenDto(jwt.getToken(), jwt.getTokenExpiresIn()), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public void logout(@RequestBody String token) {
        //String token = response.getHeader("Authorization");
        //String token = request.getHeader("Authorization");
        //DecodedJWT decodedToken=authService.getDecodedToken(token);
        authService.logout(token);
        SecurityContextHolder.getContext().setAuthentication(null);
//        그럼 이 때 리다이렉션으로 홈화면으로 이동하기
    }
}