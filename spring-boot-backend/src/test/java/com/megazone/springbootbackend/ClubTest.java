package com.megazone.springbootbackend;

import com.megazone.springbootbackend.model.entity.ClubEntity;
import com.megazone.springbootbackend.model.entity.QClubEntity;
import com.megazone.springbootbackend.repository.ClubRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.megazone.springbootbackend.model.entity.QClubEntity.clubEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClubTest {
    private String uuid = "None";
    @Autowired private EntityManager em;
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @Autowired private ClubRepository clubRepository;

    @Test
    @DisplayName("club 이름을 통한 조회")
    void selectClubTest() {
        String name = "Arsenal";
        String jpql = "select c from ClubEntity c where c.name like :name";
        TypedQuery<ClubEntity> query = em.createQuery(jpql, ClubEntity.class).setParameter("name", name);
        em.clear();
        assertThat(query.getSingleResult().getAbbr()).isEqualTo("ARS");
    }

    @Test
    @DisplayName("1부리그 club 조회")
    void selectFirstClub() {
        QClubEntity qClub = new QClubEntity("c");
        List<ClubEntity> list = jpaQueryFactory.selectFrom(qClub).where(qClub.status.eq(true)).fetch();
        for(ClubEntity club : list) {
            assertThat(club.isStatus()).isTrue();
        }
    }

    @Test
    @DisplayName("club 전체 조회")
    void selectAllClub() {
        QClubEntity qClub = new QClubEntity("c");
        List<ClubEntity> list = jpaQueryFactory.selectFrom(qClub).fetch();
        assertThat(list).hasSize(7);
    }

    @Test
    @Transactional
    @DisplayName("club 추가")
    void insertClubTest() {
        List<ClubEntity> clubs = List.of(ClubEntity.builder()
                .id(uuid)
                .name("None")
                .abbr("None")
                .website("None")
                .stadium("None")
                .status(false)
                .build());
        List<ClubEntity> list = clubRepository.saveAll(clubs);
        assertThat(list).hasSize(1);
    }

    @Test
    @Transactional
    @DisplayName("club 상태 변경")
    void updateClubTest() {
        jpaQueryFactory.update(clubEntity).set(clubEntity.status, true).where(clubEntity.id.eq(uuid));
        assertThat(jpaQueryFactory.select(clubEntity.status).from(clubEntity).fetch().get(0)).isTrue();
    }
}
