package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.model.dto.ClubAddDto;
import com.megazone.springbootbackend.model.response.FirstClubResponse;

import java.util.List;

public interface ClubService {
    void insertClubs(List<ClubAddDto> clubDtos);

    FirstClubResponse selectFirstClub(String name);

    List<FirstClubResponse> selectFirstClubs();
}
