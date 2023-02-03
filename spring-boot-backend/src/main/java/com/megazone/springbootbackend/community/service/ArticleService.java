package com.megazone.springbootbackend.community.service;

import com.megazone.springbootbackend.community.model.dto.ArticleResponseDto;
import com.megazone.springbootbackend.community.model.dto.PageResponseDto;
import com.megazone.springbootbackend.community.model.dto.SearchDto;
import com.megazone.springbootbackend.community.model.dto.SearchRankResponseDto;
import com.megazone.springbootbackend.community.model.entity.Article;
import com.megazone.springbootbackend.community.model.entity.Users;
import com.megazone.springbootbackend.community.repository.ArticleRepository;
import com.megazone.springbootbackend.community.repository.UserRepository;
import com.megazone.springbootbackend.community.util.SecurityUtil;
import com.megazone.springbootbackend.community.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.megazone.springbootbackend.community.model.entity.QArticle.article;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    private final RedisTemplate<String, String> redisTemplate;

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

    public Page<PageResponseDto> pageArticleWithSearching(int pageNum, SearchDto searchDto) {

        String searchValue="";

        if (searchDto.getNickname()!=null && searchDto.getNickname()!="") {
            searchValue= searchDto.getNickname();
        }
        if (searchDto.getTitle()!=null && searchDto.getTitle()!="") {
            searchValue= searchDto.getTitle();
        }
        if (searchDto.getContent()!=null && searchDto.getContent()!="") {
            searchValue= searchDto.getContent();
        }

        Double score = 0.0;
        try {
            redisTemplate.opsForZSet().incrementScore("ranking", searchValue,1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        redisTemplate.opsForZSet().incrementScore("ranking", searchValue, score);
        return articleRepository.searchWithPaging(PageRequest.of(pageNum - 1, 20), searchDto);
    }

    public List<SearchRankResponseDto> SearchRankList() {
        String key = "ranking";
        ZSetOperations<String, String> ZSetOperations = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = ZSetOperations.reverseRangeWithScores(key, 0, 9);  //score순으로 10개 보여줌
        return typedTuples.stream().map(SearchRankResponseDto::convertToResponseRankingDto).collect(Collectors.toList());
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