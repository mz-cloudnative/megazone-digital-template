package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.dto.ArticleResponseDto;
import com.megazone.springbootbackend.community.model.dto.PageResponseDto;
import com.megazone.springbootbackend.community.model.dto.SearchDto;
import com.megazone.springbootbackend.community.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryCustom {
    Page<PageResponseDto> searchAll(Pageable pageable);

    Page<PageResponseDto> searchByBoardId(Pageable pageable, int boardId);

    Page<PageResponseDto> searchWithPaging(Pageable pageable, SearchDto searchDto);

}
