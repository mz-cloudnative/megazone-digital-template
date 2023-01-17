package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.dto.ArticleResponseDto;
import com.megazone.springbootbackend.dto.PageResponseDto;
import com.megazone.springbootbackend.model.Article;
import com.megazone.springbootbackend.model.Users;
import com.megazone.springbootbackend.repository.ArticleRepository;
import com.megazone.springbootbackend.repository.UserRepository;
import com.megazone.springbootbackend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    private final SecurityUtil securityUtil;

    public List<PageResponseDto> allArticle(){
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(PageResponseDto::of).collect(Collectors.toList());
    }

    public ArticleResponseDto oneArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
            return ArticleResponseDto.of(article, false);
        } else {
            Users user = userRepository.findByUsername(authentication.getName()).orElseThrow();
            boolean result = article.getUsers().equals(user);
            return ArticleResponseDto.of(article, result);
        }
    }

    public Page<PageResponseDto> pageArticle(int pageNum) {
        return articleRepository.searchAll(PageRequest.of(pageNum - 1, 20));
    }

    public Page<PageResponseDto> pageArticle(int pageNum, int boardId) {
        return articleRepository.searchByBoardId(PageRequest.of(pageNum - 1, 20), boardId);
    }

    @Transactional
    public ArticleResponseDto postArticle(String title, String content, int boardId) {
        Users user = isUserCurrent();
        logger.info(user.getAuthorities().toString());
        if(user.getAuthorities().contains("USER_ADMIN")){
            boardId=1;
        }else{
            boardId=2;
        }
        Article article = Article.createArticle(title, content, user, boardId);
        return ArticleResponseDto.of(articleRepository.save(article), true);
    }

    @Transactional
    public ArticleResponseDto changeArticle(Long articleId, String title, String content) {
        Article article = authorizationArticleWriter(articleId);
        return ArticleResponseDto.of(articleRepository.save(Article.updateArticle(article, title, content)), true);
    }

    @Transactional
    public void deleteArticle(Long id) {
        Article article = authorizationArticleWriter(id);
        articleRepository.delete(article);
    }
    @Transactional
    public Users isUserCurrent() {
        return userRepository.findById(securityUtil.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    public Article authorizationArticleWriter(Long id) {
        Users user = isUserCurrent();
        Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("글이 없습니다."));
        if (!article.getUsers().equals(user)) {
            throw new RuntimeException("로그인한 유저와 작성 유저가 같지 않습니다.");
        }
        return article;
    }


}