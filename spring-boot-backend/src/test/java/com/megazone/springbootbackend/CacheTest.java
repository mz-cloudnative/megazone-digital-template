package com.megazone.springbootbackend;

import com.megazone.springbootbackend.service.PlayerService;
import com.megazone.springbootbackend.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class CacheTest {
    @Autowired private PlayerService playerService;
    @Autowired private StaffService staffService;
    @Autowired private RedisTemplate<String, Object> redisTemplate;

    private final String playerName = "Marc Roca Junque";
    private final String staffName = "None";

    @Test
    void playerCacheTest() {
        playerService.selectPlayer(playerName);
    }

    @Test
    void staffCacheTest() {
        staffService.selectSearchedStaff(staffName);
    }
}
