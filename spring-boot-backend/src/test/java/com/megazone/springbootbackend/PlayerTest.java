package com.megazone.springbootbackend;

import com.megazone.springbootbackend.model.entity.ClubEntity;
import com.megazone.springbootbackend.model.entity.PlayerEntity;
import com.megazone.springbootbackend.repository.ClubRepository;
import com.megazone.springbootbackend.repository.PlayerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.megazone.springbootbackend.model.entity.QPlayerEntity.playerEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {
    private String none = "None";
    private String test = "test";

    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Mock
    private ClubRepository clubRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeAll
    void setUp() {
        given(clubRepository.findById(none)).willReturn(Optional.ofNullable(ClubEntity.builder()
                .id(none)
                .name(none)
                .abbr(none)
                .stadium(none)
                .website(none)
                .status(true)
                .build()));
    }


    @Test
    @DisplayName("선수 조회")
    void selectPlayerTest() {
        PlayerEntity entity = jpaQueryFactory.selectFrom(playerEntity).where(playerEntity.name.eq(none)).fetch().get(0);
        assertThat(entity.getId()).isEqualTo(none);
    }

    @Test
    @Transactional
    @DisplayName("선수 등록")
    void insertPlayerTest() {
        ClubEntity club = clubRepository.findById(none).orElseThrow(RuntimeException::new);
        PlayerEntity entity = PlayerEntity.builder()
                .id(test)
                .backNumber(0)
                .club(club)
                .name(test)
                .nationality(test)
                .position(test)
                .joined(test)
                .birth(test)
                .build();
        playerRepository.save(entity);
        assertThat(playerRepository.findById(test).get().getClub().getName()).isEqualTo(none);
    }

    @Test
    @Transactional
    @DisplayName("선수 상태 변경")
    void updatePlayerTest() {
        jpaQueryFactory.update(playerEntity)
                .set(playerEntity.name,test)
                .where(playerEntity.id.eq(none))
                .execute();
        assertThat(jpaQueryFactory.selectFrom(playerEntity).where(playerEntity.id.eq(none)).fetch().get(0).getName()).isEqualTo(test);
    }
}
