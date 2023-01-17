package com.megazone.springbootbackend.sample.repository;

import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<SampleJpaEntity, Long> {

}
