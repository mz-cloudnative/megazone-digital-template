package com.megazone.springbootbackend.sampleAPI.repository;

import com.megazone.springbootbackend.sampleAPI.model.entity.SampleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sampleRepository")
public interface SampleRepository extends JpaRepository<SampleJpaEntity, Long> {

}
