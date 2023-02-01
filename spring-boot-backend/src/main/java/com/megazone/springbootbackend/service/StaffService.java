package com.megazone.springbootbackend.service;

import com.megazone.springbootbackend.model.dto.StaffDto;

import java.util.List;

public interface StaffService {
    List<StaffDto> selectStaffByClub(String clubName);

    List<StaffDto> selectAllStaff();

    List<StaffDto> selectSearchedStaff(String keyword);
}
