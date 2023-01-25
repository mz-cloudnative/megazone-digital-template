package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.entity.ClubsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubsRepository extends JpaRepository<ClubsEntity, String> {
}