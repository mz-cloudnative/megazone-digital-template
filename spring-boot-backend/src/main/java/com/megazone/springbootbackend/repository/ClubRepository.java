package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, String> {
}