package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("hotelServiceImpl")
public class HotelServiceImpl implements HotelService {


    @Autowired
    HotelRepository hotelRepositoryImpl;


    @Override
    public Hotel findAll() {
        return hotelRepositoryImpl.findAll();
    }


    //search logic


    @Override
    public ArrayList<Room> showAllRooms() {
        return findAll().getRooms();

    }

    //added guests
    @Override
    public ArrayList<Guest> showGuests() {
        return findAll().getGuests();

    }


    @Override
    public ArrayList<Room> showAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();

        for (Room room : findAll().getRooms()) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    @Override
    public ArrayList<Room> showRoomByType(String type) {
        ArrayList<Room> allRoomsOfType = new ArrayList<>();

        for (Room room : findAll().getRooms()) {
            if (room.getRoomType().equals(type)) {
                allRoomsOfType.add(room);
            }
        }
        return allRoomsOfType;
    }

    @Override
    public ArrayList<Room> showRoomsReservedByUser(Guest quest) {
        ArrayList<Room> roomsReservedByUser = new ArrayList<>();

        for (Room room : findAll().getRooms()) {
            if (quest.equals(room.getGuest())     /*room.getGuest() != null && room.getGuest().equals(quest)*/) {
                roomsReservedByUser.add(room);

            }

        }
        return roomsReservedByUser;
    }

    @Override
    public boolean reserveRoomForSpecificUser(Guest quest, String roomNumber) {
        for (Room room : findAll().getRooms()) {
            if (room.getRoomNumber().equals(roomNumber) && room.isAvailable()) {
                if (quest.getMoney() >= room.getPrice()) {
                    room.setAvailable(false);
                    room.setGuest(quest);
                    quest.setMoney(quest.getMoney() - room.getPrice());

                    return true;
                }
            }


        }


        return false;
    }


    @Override
    public boolean cancelReservation(Guest quest, String roomNumber) {
        for (Room room : findAll().getRooms()) {
            if (roomNumber.equals(room.getRoomNumber()) && quest.equals(room.getGuest()) && !room.isAvailable()) {
                room.setAvailable(true);
                room.setGuest(null);
                quest.setMoney(quest.getMoney() + room.getPrice());

                return true;
            }
        }


        return false;
    }


}
