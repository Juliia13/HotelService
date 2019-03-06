package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import com.jaspersoft.hotelServiceProject.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Guest> showAllGuests() {
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
    public Set<Room> showAvailableRooms() throws HotelServiceException {
        Set<Room> result = new HashSet<>();

        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().isAvailable()) {
                result.add(entry.getValue());
            }
        }

        if (result.isEmpty()) {
            throw new HotelServiceException("All rooms are reserved!");
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> showAvailableRooms(RoomType roomType) throws HotelServiceException {
        Set<Room> result = new HashSet<>();
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().isAvailable() && entry.getValue().getRoomType().equals(roomType)) {
                result.add(entry.getValue());
            }
        }

        if (result.isEmpty()) {
            throw new HotelServiceException("There is no available rooms of type: " + roomType);
        } else {
            return result;
        }
    }


    @Override
    public boolean reserveRoom(Guest guest, Room room) throws HotelServiceException {
        if (room.isAvailable()) {
            if (guest.getMoney() >= room.getPrice()) {
                room.setAvailable(false);
                room.setGuest(guest);
                guest.setMoney(guest.getMoney() - room.getPrice());
                return true;
            } else {
                throw new HotelServiceException("There is not enough money on your account");
            }

        } else {
            throw new HotelServiceException("The room is not available");
        }


    }


    @Override
    public boolean cancelReservation(Guest guest, Room room) throws HotelServiceException {
        if (!room.isAvailable()) {
            if (room.getGuest().equals(guest)) {
                room.setAvailable(true);
                room.setGuest(null);
                guest.setMoney(guest.getMoney() + room.getPrice());
                return true;
            } else {
                throw new HotelServiceException("The guest " + guest.getName() + " has no reservation for room " + room.getRoomNumber());
            }

        } else {
            throw new HotelServiceException("The room is already available! No reservations for specified room found");
        }


    }

}
