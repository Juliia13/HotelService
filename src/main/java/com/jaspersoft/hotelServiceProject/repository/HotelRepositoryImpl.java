package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class HotelRepositoryImpl implements HotelRepository {


    @Autowired
    @Qualifier("moonstone42")
    private Map<String, Room> rooms;

    @Autowired
    @Qualifier("moonstone42")
    private Map<String, Guest> guests;


    @Override
    public Map<String, Guest> getGuests() {
        return this.guests;
    }

    @Override
    public Map<String, Room> getRooms() {
        return this.rooms;
    }


}
