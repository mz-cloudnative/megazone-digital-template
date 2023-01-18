package com.megazone.springbootbackend.communityAPI.controller;

import com.megazone.springbootbackend.communityAPI.model.dto.*;
import com.megazone.springbootbackend.communityAPI.service.ArticleService;
import com.megazone.springbootbackend.communityAPI.service.AuthService;
import com.megazone.springbootbackend.communityAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/article")
@RequiredArgsConstructor
public class ArticleController {

    private final UserService userService;
    private final AuthService authService;
    private final ArticleService articleService;

    @PostMapping("/board/write")
    @PreAuthorize("hasAnyRole('USER')")
    public void insertArticle(/*@RequestHeader(value = "Authorization") String token, @Validated @RequestBody CreateNoticeRequestDTO requestDto*/){

        System.out.println("난 유저고 게시판 글쓰기까지 왔다");
//        Article article = Article.builder()
//                        .user(userService.findByUsername(authService.verifyToken(token)))
//                                .title()
//                                        .content()
//                                                .boardId()
//                                                        .createdDate(new Date())
//                build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<PageResponseDto>> pageArticleAll(@RequestParam(name = "page") int page) {
        return ResponseEntity.ok(articleService.pageArticle(page));
    }

    @GetMapping("/page/notice")
    public ResponseEntity<Page<PageResponseDto>> pageArticleNotice(@RequestParam(name = "page") int page) {
        return ResponseEntity.ok(articleService.pageArticle(page,1));
    }

    @GetMapping("/page/board")
    public ResponseEntity<Page<PageResponseDto>> pageArticleBoard(@RequestParam(name = "page") int page) {
        return ResponseEntity.ok(articleService.pageArticle(page,2));
    }

    @GetMapping("/one")
    public ResponseEntity<ArticleResponseDto> getOneArticle(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(articleService.oneArticle(id));
    }

    @PostMapping("/write")
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody CreateArticleRequestDto request) {
        return ResponseEntity.ok(articleService.postArticle(request.getTitle(), request.getContent(), request.getBoardId()));
    }

    @PostMapping("/write/notice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> createArticleNotice(@RequestBody CreateArticleRequestDto request) {
        return ResponseEntity.ok(articleService.postArticle(request.getTitle(), request.getContent(), request.getBoardId()));
    }

    @GetMapping("/update")
    public ResponseEntity<ArticleResponseDto> getChangeArticle(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(articleService.oneArticle((id)));
    }

    @GetMapping("/update/notice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> getChangeArticleNotice(@RequestParam(name = "id") Long id){
        return ResponseEntity.ok(articleService.oneArticle(id));
    }

    @PutMapping("/updateOK")
    public ResponseEntity<ArticleResponseDto> putChangeArticle(@RequestBody UpdateArticleRequestDto request) {
        return ResponseEntity.ok(articleService.changeArticle(
                request.getId(), request.getTitle(), request.getContent()));
    }

    @PutMapping("/updateOK/notice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ArticleResponseDto> putChangeArticleNotice(@RequestParam UpdateArticleRequestDto request){
        return ResponseEntity.ok(articleService.changeArticle(
                request.getId(), request.getTitle(), request.getContent()));
    }

    @DeleteMapping("/one")
    public ResponseEntity<MessageDto> deleteArticle(@RequestParam(name = "id") Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok(new MessageDto("Success"));
    }

}