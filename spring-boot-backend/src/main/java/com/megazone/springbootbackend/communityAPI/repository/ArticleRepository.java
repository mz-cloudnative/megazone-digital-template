package com.megazone.springbootbackend.communityAPI.repository;

import com.megazone.springbootbackend.communityAPI.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
}