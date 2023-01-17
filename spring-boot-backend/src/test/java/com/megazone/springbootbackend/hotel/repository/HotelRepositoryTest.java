package com.megazone.springbootbackend.hotel.repository;

import com.megazone.springbootbackend.hotel.model.Hotel;
import com.megazone.springbootbackend.hotel.model.Room;
import com.megazone.springbootbackend.hotel.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class HotelRepositoryTest {

    @Autowired
    private HotelRepository repository;

    Hotel hotel;
    String hotelName = "트럼프 호텔";
    @BeforeEach
    void init() {
        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName(hotelName);
        hotel.setDescription("테스트호텔");
        hotel.setAddress("경기도");
        repository.save(hotel);
    }
    @Test
    @DisplayName("호텔 아이디로 검색")
    void findHotelById() {
        Hotel foundHotel = repository.findById(1L).orElseThrow();
        System.out.println("foundHotel = " + foundHotel);
        assertThat(foundHotel.getName()).isEqualTo(hotelName);
    }

    @Test
    @DisplayName("호텔 주소 변경")
    void updateHotelAddress() {
        Hotel hotel1 = repository.findById(1L).orElseThrow();
        hotel1.setAddress("서울특별시");
        repository.save(hotel1);

        Hotel foundHotel = repository.findById(1L).orElseThrow();
        assertThat(foundHotel.getAddress()).isEqualTo("서울특별시");
    }

    @Test
    @DisplayName("방 만들기 테스트")
    void makeRoom() {
        Room room = new Room();
        room.setRoomType(RoomType.SINGE);
        room.setPrice(100000);
        room.setName("작은 싱글 룸");
        room.setDesc("저렴하게 잘 수 있는 싱글 룸 입니다.");

        List<Room> rooms = new ArrayList<>();

        rooms.add(room);

        hotel.setRooms(rooms);
        repository.save(hotel);

        Hotel hotel1 = repository.findById(1L).orElseThrow();
        List<Room> rooms1 = hotel1.getRooms();

        System.out.println("rooms = " + rooms1.get(0).getRoomType().toString());

//        hotel.setRooms();

    }

}