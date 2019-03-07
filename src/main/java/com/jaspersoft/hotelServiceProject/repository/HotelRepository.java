package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;

import java.util.Map;

public interface HotelRepository {
    Map<String, Room> getRooms();

    Map<String, Guest> getGuests();

}
