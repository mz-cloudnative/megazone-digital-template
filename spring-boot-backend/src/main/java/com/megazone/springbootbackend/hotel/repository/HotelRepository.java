package com.megazone.springbootbackend.hotel.repository;

import com.megazone.springbootbackend.hotel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
