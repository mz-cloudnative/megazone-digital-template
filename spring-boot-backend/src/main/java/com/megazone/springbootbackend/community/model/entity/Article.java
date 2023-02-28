package com.megazone.springbootbackend.community.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private long articleId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    private int boardId;

    @Column
    private int hit;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private Users users;

    public static Article createArticle (String title, String content, Users users, int boardId) {
        Article article = new Article();
        article.title = title;
        article.content = content;
        article.users = users;
        article.boardId = boardId;
        return article;
    }

    public static Article updateArticle (Article article, String title, String content) {
        article.title = title;
        article.content = content;
        return article;
    }

}
