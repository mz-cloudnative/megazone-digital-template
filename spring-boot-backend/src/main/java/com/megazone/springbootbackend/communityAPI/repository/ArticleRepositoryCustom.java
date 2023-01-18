package com.megazone.springbootbackend.communityAPI.repository;

import com.megazone.springbootbackend.communityAPI.model.dto.PageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepositoryCustom {
    Page<PageResponseDto> searchAll(Pageable pageable);

    Page<PageResponseDto> searchByBoardId(Pageable pageable, int boardId);
}
