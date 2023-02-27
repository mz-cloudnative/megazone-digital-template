package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Transactional
    @Modifying
    @Query("update Article a set a.hit = ?2  where a.articleId = ?1")
    void addHit(Long articleId, Long hit);


    @Query("select a.hit from Article a  where a.articleId = ?1")
    Long findHit(Long articleId);
}