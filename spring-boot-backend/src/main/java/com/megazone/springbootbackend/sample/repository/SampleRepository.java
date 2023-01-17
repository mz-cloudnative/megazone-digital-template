package com.megazone.springbootbackend.sample.repository;

import com.megazone.springbootbackend.sample.model.domain.SampleData;
import com.megazone.springbootbackend.sample.model.entity.SampleJpaEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<SampleJpaEntity, Long> {

  SampleData findByName(@Param("name") String name);
}
