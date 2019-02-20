package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepository {


    private Hotel moonstone42;

    @Autowired
    private List<Room> roomList;

    @Autowired
    private List<Guest> questList;


    @PostConstruct
    public void initIt() {
        moonstone42 = new Hotel(roomList, questList);
    }


    //get hotel instance
    @Override
    public Hotel findAll() {
        return this.moonstone42;
    }

    @Override
    public List<Guest> showGuests() {
        return questList;
    }

    @Override
    public List<Room> showRooms() {
        return roomList;
    }


}
