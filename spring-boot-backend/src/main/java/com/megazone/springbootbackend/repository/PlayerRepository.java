package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
}
