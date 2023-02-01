package com.megazone.springbootbackend;

import com.megazone.springbootbackend.model.entity.ClubsEntity;
import com.megazone.springbootbackend.repository.ClubsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ClubTest {
    @Mock
    ClubsRepository clubsRepository;

    EntityManager em;

    private String uuid;
//    private List<ClubsEntity> list;


    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID().toString();
//        for(int i = 0; i < 3; i++) {
//            list.add(ClubsEntity.builder()
//                            .id(String.valueOf(i+1))
//                            .name("Team " + (i+1))
//                            .abbr("TI" + (i+1))
//                            .stadium("None")
//                            .website("www")
//                            .status(true)
//                    .build());
//        }
    }

    @Test
    @DisplayName("club 추가")
    void insertClubTest() {
        List<ClubsEntity> clubs = List.of(ClubsEntity.builder()
                .id(uuid)
                .name("Team BDS")
                .abbr("BDS")
                .website("www")
                .stadium("None")
                .build());
        List<ClubsEntity> list = clubsRepository.saveAll(clubs);
        System.out.println("list = " + list);
    }

    @Test
    @DisplayName("club ID를 통한 조회")
    void selectClubTest() {
        String name = "Team BDS";
        String jpql = "select c from ClubsEntity c where c.name like :name";
        TypedQuery<ClubsEntity> query = em.createQuery(jpql, ClubsEntity.class).setParameter("name", name);
        ClubsEntity entity = query.getSingleResult();
        em.clear();
    }

    @Test
    @DisplayName("club 전체 조회")
    void selectAllClub() {
//        given(clubService.selectFirstClubs()).willReturn();
    }
}
