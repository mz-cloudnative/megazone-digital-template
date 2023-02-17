package com.megazone.springbootbackend.community.util;

import com.megazone.springbootbackend.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
//@NoArgsConstructor
@RequiredArgsConstructor
public class SecurityUtil {

    private final UserRepository userRepository;
    //private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

//    public static Optional<String> getCurrentUsername() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null) {
//            //logger.debug("Security Context에 인증 정보가 없습니다.");
//            return Optional.empty();
//        }
//
//        String username = null;
//        if (authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
//            username = springSecurityUser.getUsername();
//        } else if (authentication.getPrincipal() instanceof String) {
//            username = (String) authentication.getPrincipal();
//        }
//
//        return Optional.ofNullable(username);
//    }
//
//    public Long getCurrentUserId() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || authentication.getName() == null) {
//            throw new RuntimeException("Security Context에 인증 정보가 없습니다.");
//        }
//        System.out.println(authentication.getName());
//        Long userId;
//        userId= userRepository.findOneWithAuthoritiesByUsername(authentication.getName()).get().getUserId();
//
//        //여기 나중에 해결하고 가야함 !!!!
//        return userId;
//    }

}