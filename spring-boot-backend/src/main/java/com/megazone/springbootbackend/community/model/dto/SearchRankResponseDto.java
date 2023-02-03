package com.megazone.springbootbackend.community.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ZSetOperations;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRankResponseDto {
    String rankKeyword;
    int score;

    public static SearchRankResponseDto convertToResponseRankingDto(ZSetOperations.TypedTuple typedTuple) {
        SearchRankResponseDto searchRankResponseDtoDto = new SearchRankResponseDto();
        searchRankResponseDtoDto.rankKeyword = typedTuple.getValue().toString();
        searchRankResponseDtoDto.score = typedTuple.getScore().intValue();
        return searchRankResponseDtoDto;
    }
}
