package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestUser, Long> {

}
