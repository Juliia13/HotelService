package com.jaspersoft.service;

import com.jaspersoft.model.Guest;
import com.jaspersoft.model.Hotel;
import com.jaspersoft.model.Room;

import java.util.ArrayList;

public interface HotelService {
    Hotel findAll();

    ArrayList<Room> showAllRooms();

    ArrayList<Guest> showGuests();

    ArrayList<Room> showAvailableRooms();

    ArrayList<Room> showRoomByType(String type);

    ArrayList<Room> showRoomsReservedByUser(Guest quest);

    boolean reserveRoomForSpecificUser(Guest quest, String roomNumber);

    boolean cancelReservation(Guest quest, String roomNumber);


}
