package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.model.dto.PlayerAddDto;
import com.megazone.springbootbackend.model.dto.PlayerModifyDto;
import com.megazone.springbootbackend.model.dto.PlayerSelectDto;

public interface PlayerService {
    void addPlayer(PlayerAddDto playerAddDto);

    PlayerSelectDto selectPlayer(String name);

    void updatePlayer(PlayerModifyDto playerModifyDto);
}
