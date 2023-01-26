package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.ClubAddDto;
import com.megazone.springbootbackend.model.dto.ClubModifiedDto;
import com.megazone.springbootbackend.model.entity.ClubsEntity;
import com.megazone.springbootbackend.model.entity.QClubsEntity;
import com.megazone.springbootbackend.model.response.FirstClubResponse;
import com.megazone.springbootbackend.repository.ClubsRepository;
import com.megazone.springbootbackend.service.ClubService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    private final JPAQueryFactory jpaQueryFactory;

    private final SessionFactory sessionFactory;


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
                    .status(clubDto.getStatus().equals("Y"))
                    .build();
        }).collect(Collectors.toList());
        repository.saveAll(clubsEntities);
    }

    // JPQL 이용
    @Override
    public FirstClubResponse selectFirstClub(String name) {
        String jpql = "select c from ClubsEntity c where c.name like :name";
        TypedQuery<ClubsEntity> query = em.createQuery(jpql, ClubsEntity.class).setParameter("name", name);
        ClubsEntity entity = query.getSingleResult();
        em.clear();
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
        QClubsEntity qClubs = new QClubsEntity("c");
        List<ClubsEntity> list = jpaQueryFactory.selectFrom(qClubs).where(qClubs.status.eq(true)).fetch();
        return list.stream().map(entity -> {
            return FirstClubResponse.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .abbr(entity.getAbbr())
                    .stadium(entity.getStadium())
                    .website(entity.getWebsite())
                    .build();
        }).collect(Collectors.toList());
    }

    // HQL 이용
    @Override
    public void updateClubs(ClubModifiedDto club) {
        Session session = sessionFactory.openSession();
        String hql = "update ClubsEntity set name = :name, abbr = :abbr, website = :website, stadium = :stadium, status = :status where id like :id";
        session.createQuery(hql)
                .setParameter("id", club.getId())
                .setParameter("name", club.getName())
                .setParameter("abbr", club.getAbbr())
                .setParameter("website", club.getWebsite())
                .setParameter("stadium", club.getStadium())
                .setParameter("status", club.isStatus())
                .executeUpdate();
    }
}
