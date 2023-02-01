package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.PlayerAddDto;
import com.megazone.springbootbackend.model.dto.PlayerModifyDto;
import com.megazone.springbootbackend.model.dto.PlayerSelectDto;
import com.megazone.springbootbackend.model.entity.ClubsEntity;
import com.megazone.springbootbackend.model.entity.PlayersEntity;
import com.megazone.springbootbackend.model.entity.QClubsEntity;
import com.megazone.springbootbackend.repository.PlayersRepository;
import com.megazone.springbootbackend.service.PlayerService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.megazone.springbootbackend.model.entity.QPlayersEntity.playersEntity;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final JPAQueryFactory jpaQueryFactory;
    private final PlayersRepository playersRepository;

    @Override
    public PlayerSelectDto selectPlayer(String name) {
        PlayersEntity entity = jpaQueryFactory.selectFrom(playersEntity).where(playersEntity.name.eq(name)).fetch().get(0);
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

    // insert QueryDSL 보류 다른 방식으로 진행.
    @Override
    @Transactional
    public void addPlayer(PlayerAddDto playerAddDto) {
        String uuid = GenerateUUID.generateUUID();
        ClubsEntity club = findClub(playerAddDto.getClubId());
        PlayersEntity player = PlayersEntity.builder()
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

        playersRepository.save(player);
    }

    @Override
    @Transactional
    public void updatePlayer(PlayerModifyDto playerModifyDto) {
        ClubsEntity club = findClub(playerModifyDto.getClub());
        jpaQueryFactory.update(playersEntity)
                .where(playersEntity.id.eq(playerModifyDto.getId()))
                .set(playersEntity.name, playerModifyDto.getName())
                .set(playersEntity.backNumber, playerModifyDto.getBackNumber())
                .set(playersEntity.club, club)
                .set(playersEntity.nationality, playerModifyDto.getNationality())
                .set(playersEntity.position, playerModifyDto.getPosition())
                .set(playersEntity.joined, playerModifyDto.getJoined())
                .set(playersEntity.birth, playerModifyDto.getBirth())
                .execute();
    }


    private ClubsEntity findClub(String clubId) {
        QClubsEntity entity = QClubsEntity.clubsEntity;
        return jpaQueryFactory.selectFrom(entity).where(entity.id.eq(clubId)).fetch().get(0);
    }
}
