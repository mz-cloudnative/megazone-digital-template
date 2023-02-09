package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.dto.*;
import com.megazone.springbootbackend.model.request.*;
import com.megazone.springbootbackend.model.response.FirstClubResponse;
import com.megazone.springbootbackend.model.response.PlayerResponse;
import com.megazone.springbootbackend.model.response.StaffResponse;
import com.megazone.springbootbackend.service.ClubService;
import com.megazone.springbootbackend.service.PlayerService;
import com.megazone.springbootbackend.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ClubService clubService;
    private final PlayerService playerService;
    private final StaffService staffService;

    @GetMapping("/club")
    @Operation(description = "선택한 1부리그 팀 정보 가져오기")
    public FirstClubResponse getSelectedFirstClubInformation(@RequestParam String name) {
        return clubService.selectFirstClub(name);
    }

    @PutMapping("/club")
    @Operation(description = "클럽 업데이트")
    public void modifyTeam(@RequestBody ClubModifiedRequest request) {
        ClubModifiedDto club = ClubModifiedDto.builder()
                .id(request.getId())
                .name(request.getName())
                .abbr(request.getAbbr())
                .website(request.getWebsite())
                .stadium(request.getStadium())
                .status(request.isStatus())
                .build();
        clubService.updateClubs(club);
    }

    @GetMapping("/clubs")
    @Operation(description = "1부리그 팀들 가져오기")
    public List<FirstClubResponse> getFirstClubs() {
        return clubService.selectFirstClubs();
    }

    @GetMapping("/allClub")
    @Operation(description = "1부리그에 과거, 현재 모든 팀들 조회")
    public List<FirstClubResponse> getAllClub() {
        return clubService.selectAllFirstClub();
    }

    @PostMapping("/clubs")
    @Operation(description = "1부리그로 처음 들어오는 팀들 입력")
    public void addTeam(@RequestBody List<ClubAddRequest> request) {
        List<ClubAddDto> clubDtos = request.stream().map(req -> {
            return ClubAddDto.builder()
                    .website(req.getWebsite())
                    .stadium(req.getStadium())
                    .abbr(req.getAbbr())
                    .name(req.getName())
                    .status(req.getStatus())
                    .build();
        }).collect(Collectors.toList());
        clubService.insertClubs(clubDtos);
    }

    @GetMapping("/player")
    @Operation(description = "선수 조회")
    public PlayerResponse findPlayer(@RequestParam String name) {
        PlayerSelectDto dto = playerService.selectPlayer(name);
        return PlayerResponse.builder()
                .id(dto.getId())
                .backNumber(dto.getBackNumber())
                .name(dto.getName())
                .clubId(dto.getClubId())
                .clubName(dto.getClubName())
                .nationality(dto.getNationality())
                .position(dto.getPosition())
                .joined(dto.getJoined())
                .birth(dto.getBirth())
                .build();
    }

    @GetMapping("/allPlayer")
    @Operation(description = "모든 선수 조회")
    public Page<PlayerResponse> findAllPlayer(Pageable pageable) {
        Page<PlayerSelectDto> allPlayer = playerService.selectAllPlayer((pageable));
        return allPlayer.map(playerSelectDto -> PlayerResponse.builder()
                .id(playerSelectDto.getId())
                .backNumber(playerSelectDto.getBackNumber())
                .name(playerSelectDto.getName())
                .clubId(playerSelectDto.getClubId())
                .clubName(playerSelectDto.getClubName())
                .nationality(playerSelectDto.getNationality())
                .position(playerSelectDto.getPosition())
                .joined(playerSelectDto.getJoined())
                .birth(playerSelectDto.getBirth())
                .build());
    }

    @PostMapping("/player")
    @Operation(description = "선수 등록")
    public void addPlayer(@RequestBody PlayerAddRequest request) {
        playerService.addPlayer(
                PlayerAddDto.builder()
                        .name(request.getName())
                        .backNumber(request.getBackNumber())
                        .clubId(request.getClubId())
                        .nationality(request.getNationality())
                        .position(request.getPosition())
                        .joined(request.getJoined())
                        .birth(request.getBirth())
                        .build()
        );
    }

    @PutMapping("/player")
    @Operation(description = "선수 업데이트")
    public void updatePlayer(@RequestBody PlayerModifiedRequest request) {
        playerService.updatePlayer(PlayerModifyDto.builder()
                        .id(request.getId())
                        .backNumber(request.getBackNumber())
                        .club(request.getClub())
                        .name(request.getName())
                        .nationality(request.getNationality())
                        .position(request.getPosition())
                        .joined(request.getJoined())
                        .birth(request.getBirth())
                        .build());
    }

    @GetMapping("/staffsByClub")
    @Operation(description = "클럽별 스태프 조회")
    public List<StaffResponse> getStaffByClub(@RequestParam String clubName) {
        List<StaffDto> list = staffService.selectStaffByClub(clubName);
        return list.stream().map(staffDto -> {
            return StaffResponse.builder()
                   .id(staffDto.getId())
                   .name(staffDto.getName())
                   .clubId(staffDto.getClubId())
                   .clubName(staffDto.getClubName())
                   .role(staffDto.getRole())
                   .nationality(staffDto.getNationality())
                   .joined(staffDto.getJoined())
                   .birth(staffDto.getBirth())
                   .build();
        }).collect(Collectors.toList());
    }

    @GetMapping("/allStaff")
    @Operation(description = "전체 스태프 조회")
    public List<StaffResponse> getAllStaff() {
        List<StaffDto> list = staffService.selectAllStaff();
        return list.stream().map(staffDto -> {
            return StaffResponse.builder()
                    .id(staffDto.getId())
                    .name(staffDto.getName())
                    .clubId(staffDto.getClubId())
                    .clubName(staffDto.getClubName())
                    .role(staffDto.getRole())
                    .nationality(staffDto.getNationality())
                    .joined(staffDto.getJoined())
                    .birth(staffDto.getBirth())
                    .build();
        }).collect(Collectors.toList());
    }

    @GetMapping("/searchedStaff")
    @Operation(description = "스태프 검색")
    public List<StaffResponse> getSearchedStaff(@RequestParam String keyword) {
        List<StaffDto> list = staffService.selectSearchedStaff(keyword);
        return list.stream().map(staffDto -> {
            return StaffResponse.builder()
                    .id(staffDto.getId())
                    .name(staffDto.getName())
                    .clubId(staffDto.getClubId())
                    .clubName(staffDto.getClubName())
                    .role(staffDto.getRole())
                    .nationality(staffDto.getNationality())
                    .joined(staffDto.getJoined())
                    .birth(staffDto.getBirth())
                    .build();
        }).collect(Collectors.toList());
    }

    @PostMapping("/staffs")
    @Operation(description = "스태프 등록")
    public void addStaffs(@RequestBody List<StaffAddRequest> staffs){
        staffService.insertStaffs(staffs.stream().map(staff -> StaffDto.builder().build()).collect(Collectors.toList()));
    }
}
