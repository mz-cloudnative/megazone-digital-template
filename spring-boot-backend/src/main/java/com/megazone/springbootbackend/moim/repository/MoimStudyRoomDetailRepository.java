package com.megazone.springbootbackend.moim.repository;

import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimStudyRoomDetailRepository extends JpaRepository<MoimStudyRoomDetailEntity, Long> {

}
