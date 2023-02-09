package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.model.dto.PlayerAddDto;
import com.megazone.springbootbackend.model.dto.PlayerModifyDto;
import com.megazone.springbootbackend.model.dto.PlayerSelectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    Page<PlayerSelectDto> selectAllPlayer(Pageable pageable);

    void addPlayer(PlayerAddDto playerAddDto);

    PlayerSelectDto selectPlayer(String name);

    void updatePlayer(PlayerModifyDto playerModifyDto);
}
