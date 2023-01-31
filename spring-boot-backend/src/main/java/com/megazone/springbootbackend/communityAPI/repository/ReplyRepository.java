package com.megazone.springbootbackend.communityAPI.repository;

import com.megazone.springbootbackend.communityAPI.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {



}
