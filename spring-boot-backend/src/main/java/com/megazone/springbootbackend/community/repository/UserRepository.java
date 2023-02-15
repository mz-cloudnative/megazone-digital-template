package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.model.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByUsername(String username);
    Optional<Users> findByUsername(String username);
    Users findByTokens(Token tokens);
}

