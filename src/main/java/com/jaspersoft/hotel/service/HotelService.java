package com.jaspersoft.hotel.service;

import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;
import com.jaspersoft.hotel.model.RoomType;

import java.util.Map;
import java.util.Set;

public interface HotelService {

    /**
     * This method returns Map with all rooms
     *
     * @return Map with all rooms from repository; The key is room number with String type, the value is room with Room type
     */
    Map<String, Room> getAllRooms();

    /**
     * This method returns Map with all guests
     *
     * @return Map with all guests from repository; The key is guest name with String type, the value is guest with Guest type
     */
    Map<String, Guest> getAllGuests();

    /**
     * This method returns all guests that have reservations
     * @return Set of guests with reservations
     * @throws HotelServiceException if there are no guests with reservations
     */

    Set<Guest> getGuestsWithReservations() throws HotelServiceException;


    /**
     * This method returns room with specified number
     * @param roomNumber  room number
     * @return Room object with specified number
     * @throws HotelServiceException if room with specified roomNumber does not exist
     */
    Room getRoomByNumber(String roomNumber) throws HotelServiceException;


    /**
     * This method returns Set of rooms with specified type
     * @param type  room type
     * @return Set of rooms with specified type
     * @throws HotelServiceException if there are no rooms with specified type
     */
    Set<Room> getRooms(RoomType type) throws HotelServiceException;

    /**
     * This method returns Set of rooms reserved by specified guest
     * @param quest quest object
     * @return Set of rooms reserved by specified guest
     * @throws HotelServiceException if there are no rooms reserved by specified guest
     */
    Set<Room> getRooms(Guest quest) throws HotelServiceException;

    /**
     * This method returns Set of rooms with price in specified bounds
     * @param fromPrice  price lower bound
     * @param toPrice  price upper bound
     * @return Set of rooms with prices in specified bounds
     * @throws HotelServiceException if there are no rooms with specified price bounds
     */

    Set<Room> getRooms(double fromPrice, double toPrice) throws HotelServiceException;

    /**
     * This method returns guest with specified name
     * @param name  guest name
     * @return guest with specified name
     * @throws HotelServiceException if there are no guest with specified name
     */
    Guest getGuest(String name) throws HotelServiceException;

    /**
     * This method returns Set of available rooms
     * @return Set of available rooms
     * @throws HotelServiceException if there are no available rooms
     */
    Set<Room> getAvailableRooms() throws HotelServiceException;

    /**
     * This method returns Set of available rooms of specified type
     * @param roomType  room type
     * @return Set of available rooms of specified type
     * @throws HotelServiceException if there are no available rooms of specified type
     */

    Set<Room> getAvailableRooms(RoomType roomType) throws HotelServiceException;


    /**
     * This method reserves specified room for specified guest
     * @param guest  guest object
     * @param room  room object
     * @return true when reservation is successful
     * @throws HotelServiceException when there is not enough money on guest's account
     * @throws HotelServiceException when room is not available
     */
    boolean reserveRoom(Guest guest, Room room) throws HotelServiceException;


    /**
     * This method cancels reservation of specified room with specified guest
     * @param quest  guest object
     * @param room  room object
     * @return true when reservation is cancelled
     * @throws HotelServiceException when specified guest doesn't have reservation for specified room
     * @throws HotelServiceException when the room is already available
     */
    boolean cancelReservation(Guest quest, Room room) throws HotelServiceException;


}
