package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    //@Autowired
    private TokenRepository tokenRepository;

    //@Autowired
    private UserService userService;

//    public Token findTokenByUserId(long userId) {
//        User user = this.userService.findUserById(userId);
//        Optional<Token> token = this.tokenRepository.findByUserId(user.getUserId());
//        if (token.isPresent()) {
//            return token.get();
//        } else {
//            throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
//        }
//    }
}
