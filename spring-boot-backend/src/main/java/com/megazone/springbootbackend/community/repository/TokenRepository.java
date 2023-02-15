package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUsers(Users user);
//
//    void deleteByUsername(String username);
    @Transactional
    void deleteByTokenName(String tokenName);

    Token findByTokenName(String tokenName);

}
