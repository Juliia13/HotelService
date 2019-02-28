package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import com.jaspersoft.hotelServiceProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("hotelServiceImpl")
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;


    //search logic

    @Override
    public List<Room> showAllRooms() {
        return hotelRepository.getRooms();

    }


    @Override
    public List<Guest> showGuests() {
        return hotelRepository.getGuests();

    }


    @Override
    public ArrayList<Room> showAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : hotelRepository.getRooms()) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public ArrayList<Room> showRoomByType(RoomType type) {
        ArrayList<Room> allRoomsOfType = new ArrayList<>();

        for (Room room : hotelRepository.getRooms()) {
            if (room.getRoomType().equals(type)) {
                allRoomsOfType.add(room);
            }
        }
        return allRoomsOfType;
    }

    @Override
    public ArrayList<Room> showRoomsReservedByUser(Guest quest) throws HotelServiceException {
        ArrayList<Room> roomsReservedByUser = new ArrayList<>();
        for (Room room : hotelRepository.getRooms()) {
            if (quest.equals(room.getGuest())) {
                roomsReservedByUser.add(room);
            }
        }


        if (roomsReservedByUser.isEmpty()) {
            throw new HotelServiceException("There is no reservations for quest " + quest.getName());
        } else {
            return roomsReservedByUser;
        }


    }

    @Override
    public boolean reserveRoomByNumber(Guest quest, String roomNumber) throws HotelServiceException {
        for (Room room : hotelRepository.getRooms()) {

            if (room.getRoomNumber().equals(roomNumber) && room.isAvailable()) {
                if (quest.getMoney() >= room.getPrice()) {
                    room.setAvailable(false);
                    room.setGuest(quest);
                    quest.setMoney(quest.getMoney() - room.getPrice());

                    return true;
                } else {
                    throw new HotelServiceException("There is not enough money on your account! The room price is " + room.getPrice() + ". There is " + quest.getMoney() + " on your account");
                }


            }


        }

        throw new HotelServiceException("There is no such room available: " + roomNumber);

    }


    @Override
    public boolean reserveRoomByType(Guest quest, RoomType roomType) throws HotelServiceException {
        Room room = getAvailableRoomByType(roomType);
        if (quest.getMoney() >= room.getPrice()) {
            room.setAvailable(false);
            room.setGuest(quest);
            quest.setMoney(quest.getMoney() - room.getPrice());
            return true;
        } else {
            throw new HotelServiceException("There is not enough money on your account! The room price is " + room.getPrice() + ". There is " + quest.getMoney() + " on your account");
        }

    }

    private Room getAvailableRoomByType(RoomType type) throws HotelServiceException {
        for (Room room : showAvailableRooms()) {
            if (room.getRoomType().equals(type)) {
                return room;
            }
        }
        throw new HotelServiceException("There is no room of type: " + type + " available");
    }


    @Override
    public boolean cancelReservation(Guest quest, String roomNumber) throws HotelServiceException {
        for (Room room : hotelRepository.getRooms()) {
            if (roomNumber.equals(room.getRoomNumber()) && quest.equals(room.getGuest()) && !room.isAvailable()) {
                room.setAvailable(true);
                room.setGuest(null);
                quest.setMoney(quest.getMoney() + room.getPrice());

                return true;
            }
        }


        throw new HotelServiceException("There is no reservations of room" + roomNumber + " for quest " + quest.getName());
    }


}
