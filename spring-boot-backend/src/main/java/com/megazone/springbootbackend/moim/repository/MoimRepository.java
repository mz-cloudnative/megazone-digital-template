package com.megazone.springbootbackend.moim.repository;

import com.megazone.springbootbackend.moim.model.entity.MoimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoimRepository extends JpaRepository<MoimEntity, Long> {

}
