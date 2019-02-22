package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;

import java.util.ArrayList;
import java.util.List;

public interface HotelService {


    List<Room> showAllRooms();

    List<Guest> showGuests();

    ArrayList<Room> showAvailableRooms();

    ArrayList<Room> showRoomByType(String type);

    ArrayList<Room> showRoomsReservedByUser(Guest quest);

    boolean reserveRoomForSpecificUser(Guest quest, String roomNumber) throws HotelServiceException;

    boolean cancelReservation(Guest quest, String roomNumber) throws HotelServiceException;


}
