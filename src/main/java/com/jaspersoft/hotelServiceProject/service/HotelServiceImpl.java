package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import com.jaspersoft.hotelServiceProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service("hotelServiceImpl")
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;


    //search logic

    @Override
    public Map<String, Room> showAllRooms() {
        return hotelRepository.getRooms();

    }


    @Override
    public Map<String, Guest> showGuests() {
        return hotelRepository.getGuests();

    }


    @Override
    public ArrayList<Room> showAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().isAvailable()) {
                availableRooms.add(entry.getValue());
            }
        }

        return availableRooms;
    }

    @Override
    public ArrayList<Room> showRoomByType(RoomType type) {
        ArrayList<Room> allRoomsOfType = new ArrayList<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().getRoomType().equals(type)) {
                allRoomsOfType.add(entry.getValue());
            }
        }

        return allRoomsOfType;
    }

    @Override
    public ArrayList<Room> showRoomsReservedByUser(Guest quest) throws HotelServiceException {
        ArrayList<Room> roomsReservedByUser = new ArrayList<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (quest.equals(entry.getValue().getGuest())) {
                roomsReservedByUser.add(entry.getValue());
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

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().getRoomNumber().equals(roomNumber) && entry.getValue().isAvailable()) {
                if (quest.getMoney() >= entry.getValue().getPrice()) {
                    entry.getValue().setAvailable(false);
                    entry.getValue().setGuest(quest);
                    quest.setMoney(quest.getMoney() - entry.getValue().getPrice());

                    return true;
                } else {
                    throw new HotelServiceException("There is not enough money on your account! The room price is " + entry.getValue().getPrice() + ". There is " + quest.getMoney() + " on your account");
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

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (roomNumber.equals(entry.getValue().getRoomNumber()) && quest.equals(entry.getValue().getGuest()) && !entry.getValue().isAvailable()) {
                entry.getValue().setAvailable(true);
                entry.getValue().setGuest(null);
                quest.setMoney(quest.getMoney() + entry.getValue().getPrice());

                return true;
            }
        }


        throw new HotelServiceException("There is no reservations of room" + roomNumber + " for quest " + quest.getName());
    }


}
