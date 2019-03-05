package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import com.jaspersoft.hotelServiceProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public Set<Guest> showGuestsWithReservations() throws HotelServiceException {
        Set<Guest> result = new HashSet<>();
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().getGuest() != null) {
                result.add(entry.getValue().getGuest());
            }
        }

        if (result.isEmpty()) {
            throw new HotelServiceException("There is no reservations in this hotel");
        } else {
            return result;
        }

    }


    @Override
    public Room showRoomByNumber(String roomNumber) throws HotelServiceException {
        if (hotelRepository.getRooms().containsKey(roomNumber)) {
            return hotelRepository.getRooms().get(roomNumber);
        } else {
            throw new HotelServiceException("The room with number '" + roomNumber + "' doesn't exist");
        }
    }


    @Override
    public Set<Room> showRooms(RoomType type) throws HotelServiceException {
        Set<Room> result = new HashSet<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().getRoomType().equals(type)) {
                result.add(entry.getValue());
            }
        }

        if (result.isEmpty()) {
            throw new HotelServiceException("There is no rooms of type " + type + " in hotel");
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> showRooms(Guest quest) throws HotelServiceException {
        Set<Room> result = new HashSet<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (quest.equals(entry.getValue().getGuest())) {
                result.add(entry.getValue());
            }

        }


        if (result.isEmpty()) {
            throw new HotelServiceException("There is no reservations for quest " + quest.getName());
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> showRooms(double fromPrice, double toPrice) throws HotelServiceException {
        Set<Room> result = new HashSet<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().getPrice() >= fromPrice && entry.getValue().getPrice() <= toPrice) {
                result.add(entry.getValue());
            }

        }


        if (result.isEmpty()) {
            throw new HotelServiceException("These is no rooms in specified price boundaries");
        } else {
            return result;
        }


    }


    @Override
    public Guest showGuest(String name) throws HotelServiceException {
        if (hotelRepository.getGuests().containsKey(name)) {
            return hotelRepository.getGuests().get(name);
        } else {
            throw new HotelServiceException("The guest with name '" + name + "' doesn't exist");
        }

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
