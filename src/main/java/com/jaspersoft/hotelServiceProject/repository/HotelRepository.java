package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;

import java.util.ArrayList;

public interface HotelRepository {
    Hotel findAll();

    ArrayList<Guest> showGuests();

    ArrayList<Room> showRooms();
}
