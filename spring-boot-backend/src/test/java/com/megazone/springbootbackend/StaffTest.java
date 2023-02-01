package com.megazone.springbootbackend;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.StaffDto;
import com.megazone.springbootbackend.model.entity.ClubEntity;
import com.megazone.springbootbackend.model.entity.StaffEntity;
import com.megazone.springbootbackend.repository.ClubRepository;
import com.megazone.springbootbackend.repository.StaffRepository;
import com.megazone.springbootbackend.serviceImpl.StaffServiceImpl;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.megazone.springbootbackend.model.entity.QClubEntity.clubEntity;
import static com.megazone.springbootbackend.model.entity.QStaffEntity.staffEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StaffTest {
    @Mock
    private ClubRepository clubRepository;
    @InjectMocks
    private StaffServiceImpl staffService;
    @Mock
    private JPAQueryFactory jpaQueryFactory;
    @Mock
    private JPAQuery<ClubEntity> jpaClubQuery;
    @Mock
    private JPAQuery<StaffEntity> jpaStaffQuery;
    @Mock
    private StaffRepository staffRepository;

    private static String NONE = "None";
    private final String rightKeyword =  "No";
    private final String wrongKeyword =  "Noe";

    final ClubEntity noneClub = ClubEntity.builder()
            .id(NONE)
            .name(NONE)
            .abbr(NONE)
            .stadium(NONE)
            .website(NONE)
            .status(false)
            .build();

    private final List<StaffEntity> allStaff = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for(int i = 0; i < 3; i++) {
            allStaff.add(StaffEntity.builder()
                    .id("id"+i)
                            .name("name"+i)
                            .club(noneClub)
                            .nationality(NONE)
                            .role("role"+i)
                            .joined(String.valueOf(i))
                            .birth(String.valueOf(i))
                    .build());
        }
    }

    @Test
    @DisplayName("해당 클럽의 스태프")
    void selectStaffsByClubTest() {

        when(clubRepository.findById(NONE)).thenReturn(Optional.ofNullable(ClubEntity.builder()
                .id(NONE)
                .name(NONE)
                .abbr(NONE)
                .stadium(NONE)
                .website(NONE)
                .status(false)
                .build()));

        ClubEntity club = clubRepository.findById(NONE).orElseThrow(RuntimeException::new);

        given(jpaQueryFactory.selectFrom(clubEntity)).willReturn(jpaClubQuery);
        given(jpaClubQuery.where(clubEntity.name.eq(club.getName()))).willReturn(jpaClubQuery);
        given(jpaClubQuery.fetch()).willReturn(List.of(noneClub));

        final StaffEntity noneStaff = StaffEntity.builder()
                .id(NONE)
                .club(club)
                .nationality(NONE)
                .name(NONE)
                .role(NONE)
                .joined(NONE)
                .birth(NONE)
                .build();

        given(jpaQueryFactory.selectFrom(staffEntity)).willReturn(jpaStaffQuery);
        given(jpaStaffQuery.where(any(Predicate.class))).willReturn(jpaStaffQuery);
        given(jpaStaffQuery.fetch()).willReturn(List.of(noneStaff));

        List<StaffDto> list = staffService.selectStaffByClub(club.getName());
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getClubName()).isEqualTo(NONE);
    }

    @Test
    @DisplayName("스태프 전제 조회")
    void selectAllStaffTest() {
        given(jpaQueryFactory.selectFrom(staffEntity)).willReturn(jpaStaffQuery);
        given(jpaStaffQuery.fetch()).willReturn(allStaff);

        List<StaffDto> list = staffService.selectAllStaff();
        assertThat(list).hasSize(3);
    }

    @Test
    @DisplayName("스태프 검색")
    void searchStaff() {
        matchGiven(rightKeyword);
        assertThat(staffService.selectSearchedStaff(rightKeyword)).hasSize(1);

        matchGiven(wrongKeyword);
        assertThrows(NullPointerException.class, () -> staffService.selectSearchedStaff(wrongKeyword));
    }

    @Test
    @DisplayName("스태프 등록")
    void insertStaff() {
        StaffEntity staff = StaffEntity.builder()
                .id(NONE)
                .club(noneClub)
                .name(NONE)
                .nationality(NONE)
                .role(NONE)
                .birth(NONE)
                .joined(NONE)
                .build();
        given(staffRepository.save(staff)).willReturn(staff);
        staffService.addStaffs()
    }

    private void matchGiven(String keyword) {
        String regex = "(.*)" + keyword + "(.*)";
        given(jpaQueryFactory.selectFrom(staffEntity)).willReturn(jpaStaffQuery);
        lenient().when(jpaStaffQuery.where(staffEntity.name.contains(keyword))).thenReturn(jpaStaffQuery);

        if(noneClub.getName().matches(regex)) {
            final StaffEntity staff = StaffEntity.builder()
                    .id(NONE)
                    .name(NONE)
                    .club(noneClub)
                    .nationality(NONE)
                    .role(NONE)
                    .joined(NONE)
                    .birth(NONE)
                    .build();
            given(jpaStaffQuery.fetch()).willReturn(List.of(staff));
        }
        else {
            given(jpaStaffQuery.fetch()).willThrow(NullPointerException.class);
        }
    }
}
