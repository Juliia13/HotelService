package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepository {
    private Hotel hotel;

    @Autowired
    public void setHotel(List<Room> rooms, List<Guest> questList) {
        this.hotel = new Hotel(rooms, questList);
    }



    @Autowired
    private List<Room> roomList;

    @Autowired
    private List<Guest> questList;


//    @PostConstruct
//    public void initIt() {
//        hotel = new Hotel(roomList, questList);
//    }


    //get hotel instance
    @Override
    public Hotel findAll() {
        return this.hotel;
    }

    @Override
    public List<Guest> showGuests() {
        return this.questList;
    }

    @Override
    public List<Room> showRooms() {
        return this.roomList;
    }


}
