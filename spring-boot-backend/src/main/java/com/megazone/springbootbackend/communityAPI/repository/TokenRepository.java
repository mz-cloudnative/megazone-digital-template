package com.megazone.springbootbackend.communityAPI.repository;

import com.megazone.springbootbackend.communityAPI.model.entity.Token;
import com.megazone.springbootbackend.communityAPI.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUsers(Users user);
//
//    void deleteByUsername(String username);
    @Transactional
    void deleteByTokenName(String tokenName);

    Token findByTokenName(String tokenName);

}
