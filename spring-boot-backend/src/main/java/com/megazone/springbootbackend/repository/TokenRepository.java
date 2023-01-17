package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.Token;
import com.megazone.springbootbackend.model.Users;
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
