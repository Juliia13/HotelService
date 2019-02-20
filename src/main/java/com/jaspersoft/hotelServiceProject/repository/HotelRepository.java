package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;

import java.util.List;

public interface HotelRepository {
    Hotel findAll();

    List<Guest> showGuests();

    List<Room> showRooms();
}
