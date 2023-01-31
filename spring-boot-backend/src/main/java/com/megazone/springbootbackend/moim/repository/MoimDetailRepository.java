package com.megazone.springbootbackend.moim.repository;

import com.megazone.springbootbackend.moim.model.entity.MoimDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimDetailRepository extends JpaRepository<MoimDetailEntity, Long> {

}
