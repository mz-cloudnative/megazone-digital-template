package com.megazone.springbootbackend.controller;

import com.megazone.springbootbackend.model.dto.ClubAddDto;
import com.megazone.springbootbackend.model.dto.ClubModifiedDto;
import com.megazone.springbootbackend.model.request.ClubAddRequest;
import com.megazone.springbootbackend.model.request.ClubModifiedRequest;
import com.megazone.springbootbackend.model.request.PlayerAddRequest;
import com.megazone.springbootbackend.model.response.FirstClubResponse;
import com.megazone.springbootbackend.service.ClubService;
import com.megazone.springbootbackend.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {
    private final ClubService clubService;
    private final PlayerService playerService;

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

    @PostMapping("/player")
    @Operation(description = "선수 등록")
    public void addPlayer(@RequestBody PlayerAddRequest request) {
        playerService.addPlayer();
    }
}
