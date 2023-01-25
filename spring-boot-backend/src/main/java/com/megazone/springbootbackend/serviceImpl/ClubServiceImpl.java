package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.ClubAddDto;
import com.megazone.springbootbackend.model.entity.ClubsEntity;
import com.megazone.springbootbackend.model.response.FirstClubResponse;
import com.megazone.springbootbackend.repository.ClubsRepository;
import com.megazone.springbootbackend.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubServiceImpl implements ClubService {
    private final ClubsRepository repository;
    private final EntityManager em;

    // 일반적인 것.
    @Override
    @Transactional
    public void insertClubs(List<ClubAddDto> clubDtos) {
        List<ClubsEntity> clubsEntities = clubDtos.stream().map(clubDto -> {
            String uuid = GenerateUUID.generateUUID();
            return ClubsEntity.builder()
                    .id(uuid)
                    .abbr(clubDto.getAbbr())
                    .website(clubDto.getWebsite())
                    .stadium(clubDto.getStadium())
                    .name(clubDto.getName())
                    .build();
        }).collect(Collectors.toList());
        repository.saveAll(clubsEntities);
    }

    // JPQL 이용
    @Override
    public FirstClubResponse selectFirstClub(String name) {
        String jpql = "select c from ClubsEntity c where c.name like :name";
        TypedQuery<ClubsEntity> query = em.createQuery(jpql, ClubsEntity.class);
        query.setParameter("name", name);
        ClubsEntity entity = query.getSingleResult();
        return FirstClubResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .abbr(entity.getAbbr())
                .stadium(entity.getStadium())
                .website(entity.getWebsite())
                .build();
    }

    // queryDSL 이용
    @Override
    public List<FirstClubResponse> selectFirstClubs() {
        return null;
    }

    // HQL 이용
    public void updateStatus(String id) {

    }
}
