package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.entity.ClubsEntity;
import com.megazone.springbootbackend.model.entity.RanksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RanksRepository extends JpaRepository<RanksEntity, ClubsEntity> {
}
