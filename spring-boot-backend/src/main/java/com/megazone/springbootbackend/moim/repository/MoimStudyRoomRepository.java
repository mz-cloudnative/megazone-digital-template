package com.megazone.springbootbackend.moim.repository;

import com.megazone.springbootbackend.moim.model.entity.MoimStudyRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoimStudyRoomRepository extends JpaRepository<MoimStudyRoomEntity, Long> {

}
