package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {



}
