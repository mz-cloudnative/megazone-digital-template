package com.megazone.springbootbackend.moim.repository;

import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimRepository extends JpaRepository<MoimEntity, Long> {

}
