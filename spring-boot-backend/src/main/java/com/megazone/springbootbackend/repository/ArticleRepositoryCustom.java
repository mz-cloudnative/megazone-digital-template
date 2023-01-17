package com.megazone.springbootbackend.repository;

import com.megazone.springbootbackend.dto.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<PageResponseDto> searchAll(Pageable pageable);

    Page<PageResponseDto> searchByBoardId(Pageable pageable, int boardId);
}
