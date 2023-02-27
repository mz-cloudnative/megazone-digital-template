package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Token;
import com.megazone.springbootbackend.community.model.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Users> findOneWithAuthoritiesByUsername(String username);
    Optional<Users> findByUsername(String username);
    Users findByTokens(Token tokens);

    @Query("select u from Users u where u.username= ?1")
    Users findByUsername2(String username);
}

