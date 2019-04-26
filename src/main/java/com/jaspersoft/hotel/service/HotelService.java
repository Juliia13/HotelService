package com.jaspersoft.hotel.service;

import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;
import com.jaspersoft.hotel.model.RoomType;

import java.util.Map;
import java.util.Set;

public interface HotelService {


    Map<String, Room> getAllRooms();

    Map<String, Guest> getAllGuests();

    Set<Guest> getGuestsWithReservations() throws HotelServiceException;

    Room getRoomByNumber(String roomNumber) throws HotelServiceException;

    Set<Room> getRooms(RoomType type) throws HotelServiceException;

    Set<Room> getRooms(Guest quest) throws HotelServiceException;

    Set<Room> getRooms(double fromPrice, double toPrice) throws HotelServiceException;

    Guest getGuest(String name) throws HotelServiceException;

    Set<Room> getAvailableRooms() throws HotelServiceException;

    Set<Room> getAvailableRooms(RoomType roomType) throws HotelServiceException;


    boolean reserveRoom(Guest guest, Room room) throws HotelServiceException;

    boolean cancelReservation(Guest quest, Room room) throws HotelServiceException;


}
