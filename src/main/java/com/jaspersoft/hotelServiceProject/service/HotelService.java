package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface HotelService {


    Map<String, Room> showAllRooms();

    Map<String, Guest> showGuests();

    Set<Guest> showGuestsWithReservations() throws HotelServiceException;

    Room showRoomByNumber(String roomNumber) throws HotelServiceException;

    Set<Room> showRooms(RoomType type) throws HotelServiceException;

    Set<Room> showRooms(Guest quest) throws HotelServiceException;

    Set<Room> showRooms(double fromPrice, double toPrice) throws HotelServiceException;

    Guest showGuest(String name) throws HotelServiceException;






    ArrayList<Room> showAvailableRooms();


    //  ArrayList<Room> showRoomsReservedByUser(Guest quest) throws HotelServiceException;

    boolean reserveRoomByNumber(Guest quest, String roomNumber) throws HotelServiceException;

    boolean reserveRoomByType(Guest quest, RoomType roomType) throws HotelServiceException;

    boolean cancelReservation(Guest quest, String roomNumber) throws HotelServiceException;


}
