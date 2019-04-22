package com.jaspersoft.hotel.repository;

import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;

import java.util.Map;

public interface HotelRepository {
    Map<String, Room> getRooms();

    Map<String, Guest> getGuests();

}
