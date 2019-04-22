package com.jaspersoft.hotel.service;

import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;
import com.jaspersoft.hotel.model.RoomType;

import java.util.Map;
import java.util.Set;

public interface HotelService {


    Map<String, Room> showAllRooms();

    Map<String, Guest> showAllGuests();

    Set<Guest> showGuestsWithReservations() throws HotelServiceException;

    Room showRoomByNumber(String roomNumber) throws HotelServiceException;

    Set<Room> showRooms(RoomType type) throws HotelServiceException;

    Set<Room> showRooms(Guest quest) throws HotelServiceException;

    Set<Room> showRooms(double fromPrice, double toPrice) throws HotelServiceException;

    Guest showGuest(String name) throws HotelServiceException;

    Set<Room> showAvailableRooms() throws HotelServiceException;

    Set<Room> showAvailableRooms(RoomType roomType) throws HotelServiceException;


    boolean reserveRoom(Guest guest, Room room) throws HotelServiceException;

    boolean cancelReservation(Guest quest, Room room) throws HotelServiceException;


}
