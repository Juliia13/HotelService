package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepository {


    @Autowired
    @Qualifier("moonstone42")
    private List<Room> rooms;

    @Autowired
    @Qualifier("moonstone42")
    private List<Guest> guests;


    @Override
    public List<Guest> getGuests() {
        return this.guests;
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }


}
