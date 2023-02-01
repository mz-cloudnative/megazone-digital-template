package com.megazone.springbootbackend.community.repository;

import com.megazone.springbootbackend.community.model.dto.PageResponseDto;
import com.megazone.springbootbackend.community.model.dto.SearchDto;
import com.megazone.springbootbackend.community.model.entity.Article;
import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.junit.Assert.assertThat;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ArticleRepositoryImplTest {

    @Mock
    ArticleRepository articleRepository;

    @Test
    @DisplayName("검색 테스트")
    void 검색테스트(){
        SearchDto searchDto = new SearchDto("jinjoo2", null, null);

        //List<Article> result = articleRepository.search(searchDto);

        //assertEquals(result.size(),3);
    }

}