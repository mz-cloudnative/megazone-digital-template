package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
}