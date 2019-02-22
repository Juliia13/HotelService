package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;

import java.util.List;

public interface HotelRepository {


    List<Guest> getGuests();

    List<Room> getRooms();
}
