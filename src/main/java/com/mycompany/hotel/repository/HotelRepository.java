package com.mycompany.hotel.repository;

import com.mycompany.hotel.model.Guest;
import com.mycompany.hotel.model.Room;

import java.util.Map;

public interface HotelRepository {
    Map<String, Room> getRooms();

    Map<String, Guest> getGuests();

}
