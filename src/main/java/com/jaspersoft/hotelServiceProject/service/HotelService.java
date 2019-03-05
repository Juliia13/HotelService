package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;

import java.util.ArrayList;
import java.util.Map;

public interface HotelService {


    Map<String, Room> showAllRooms();

    Map<String, Guest> showGuests();

    ArrayList<Room> showAvailableRooms();

    ArrayList<Room> showRoomByType(RoomType type);

    ArrayList<Room> showRoomsReservedByUser(Guest quest) throws HotelServiceException;

    boolean reserveRoomByNumber(Guest quest, String roomNumber) throws HotelServiceException;

    boolean reserveRoomByType(Guest quest, RoomType roomType) throws HotelServiceException;

    boolean cancelReservation(Guest quest, String roomNumber) throws HotelServiceException;


}
