package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
}
