package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.PlayerAddDto;
import com.megazone.springbootbackend.model.dto.PlayerModifyDto;
import com.megazone.springbootbackend.model.dto.PlayerSelectDto;
import com.megazone.springbootbackend.model.entity.ClubEntity;
import com.megazone.springbootbackend.model.entity.PlayerEntity;
import com.megazone.springbootbackend.model.entity.QClubEntity;
import com.megazone.springbootbackend.repository.PlayerRepository;
import com.megazone.springbootbackend.service.PlayerService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.megazone.springbootbackend.model.entity.QPlayerEntity.playerEntity;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final JPAQueryFactory jpaQueryFactory;
    private final PlayerRepository playerRepository;

    @Override
    public PlayerSelectDto selectPlayer(String name) {
        PlayerEntity entity = jpaQueryFactory.selectFrom(playerEntity).where(playerEntity.name.eq(name)).fetch().get(0);
        return PlayerSelectDto.builder()
                .id(entity.getId())
                .backNumber(entity.getBackNumber())
                .name(entity.getName())
                .clubId(entity.getClub().getId())
                .clubName(entity.getClub().getName())
                .nationality(entity.getNationality())
                .position(entity.getPosition())
                .joined(entity.getJoined())
                .birth(entity.getBirth())
                .build();
    }

    @Override
    public List<PlayerSelectDto> selectAllPlayer() {
        return null;
    }
    // insert QueryDSL 보류 다른 방식으로 진행.
    @Override
    @Transactional
    public void addPlayer(PlayerAddDto playerAddDto) {
        String uuid = GenerateUUID.generateUUID();
        ClubEntity club = findClub(playerAddDto.getClubId());
        PlayerEntity player = PlayerEntity.builder()
                .id(uuid)
                .backNumber(playerAddDto.getBackNumber())
                .club(club)
                .name(playerAddDto.getName())
                .nationality(playerAddDto.getNationality())
                .position(playerAddDto.getPosition())
                .joined(playerAddDto.getJoined())
                .birth(playerAddDto.getBirth())
                .build();

//        jpaQueryFactory.insert(playersEntity)
//                .set(playersEntity.id, uuid)
//                .set(playersEntity.backNumber, playerAddDto.getBackNumber())
//                .set(playersEntity.club, club)
//                .set(playersEntity.name, playerAddDto.getName())
//                .set(playersEntity.nationality, playerAddDto.getNationality())
//                .set(playersEntity.position, playerAddDto.getPosition())
//                .set(playersEntity.joined, playerAddDto.getJoined())
//                .set(playersEntity.birth, playerAddDto.getBirth())
//                .execute();

        playerRepository.save(player);
    }

    @Override
    @Transactional
    public void updatePlayer(PlayerModifyDto playerModifyDto) {
        ClubEntity club = findClub(playerModifyDto.getClub());
        jpaQueryFactory.update(playerEntity)
                .set(playerEntity.name, playerModifyDto.getName())
                .set(playerEntity.backNumber, playerModifyDto.getBackNumber())
                .set(playerEntity.club, club)
                .set(playerEntity.nationality, playerModifyDto.getNationality())
                .set(playerEntity.position, playerModifyDto.getPosition())
                .set(playerEntity.joined, playerModifyDto.getJoined())
                .set(playerEntity.birth, playerModifyDto.getBirth())
                .where(playerEntity.id.eq(playerModifyDto.getId()))
                .execute();
    }


    private ClubEntity findClub(String clubId) {
        QClubEntity entity = QClubEntity.clubEntity;
        return jpaQueryFactory.selectFrom(entity).where(entity.id.eq(clubId)).fetch().get(0);
    }
}
