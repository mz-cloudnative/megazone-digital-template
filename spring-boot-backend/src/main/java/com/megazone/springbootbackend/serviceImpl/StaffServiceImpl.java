package com.megazone.springbootbackend.serviceImpl;

import com.megazone.springbootbackend.common.GenerateUUID;
import com.megazone.springbootbackend.model.dto.StaffDto;
import com.megazone.springbootbackend.model.entity.ClubEntity;
import com.megazone.springbootbackend.model.entity.QClubEntity;
import com.megazone.springbootbackend.model.entity.StaffEntity;
import com.megazone.springbootbackend.repository.StaffRepository;
import com.megazone.springbootbackend.service.StaffService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.megazone.springbootbackend.model.entity.QStaffEntity.staffEntity;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final JPAQueryFactory jpaQueryFactory;
    private final StaffRepository staffRepository;
    @Override
    public List<StaffDto> selectStaffByClub(String clubName) {
        ClubEntity club = findClub(clubName);
        List<StaffEntity> list = jpaQueryFactory.selectFrom(staffEntity).where(staffEntity.club.eq(club)).fetch();
        return getStaffDtos(list);
    }

    @Override
    public List<StaffDto> selectAllStaff() {
        List<StaffEntity> staffs = jpaQueryFactory.selectFrom(staffEntity).fetch();
        return getStaffDtos(staffs);
    }

    @Override
    public List<StaffDto> selectSearchedStaff(String keyword) {
        List<StaffEntity> staffs = jpaQueryFactory.selectFrom(staffEntity).where(staffEntity.name.contains(keyword)).fetch();
        return getStaffDtos(staffs);
    }

    @Override
    public void insertStaffs(List<StaffDto> list) {
        List<StaffEntity> entities = new ArrayList<>();
        for (StaffDto staff : list) {
            String uuid = GenerateUUID.generateUUID();
            entities.add(StaffEntity.builder()
                            .id(uuid)
                            .name(staff.getName())
                            .club(findClub(staff.getClubName()))
                            .nationality(staff.getNationality())
                            .role(staff.getRole())
                            .joined(staff.getJoined())
                            .birth(staff.getBirth())
                    .build());
        }
        staffRepository.saveAll(entities);
    }

    private List<StaffDto> getStaffDtos(List<StaffEntity> staffs) {
        return staffs.stream().map(staff ->
            StaffDto.builder()
                    .id(staff.getId())
                    .name(staff.getName())
                    .clubId(staff.getClub().getId())
                    .clubName(staff.getClub().getName())
                    .nationality(staff.getNationality())
                    .role(staff.getRole())
                    .joined(staff.getJoined())
                    .birth(staff.getBirth())
                    .build()
        ).collect(Collectors.toList());
    }

    private ClubEntity findClub(String clubName) {
        QClubEntity entity = QClubEntity.clubEntity;
        return jpaQueryFactory.selectFrom(entity).where(entity.name.eq(clubName)).fetch().get(0);
    }
}
