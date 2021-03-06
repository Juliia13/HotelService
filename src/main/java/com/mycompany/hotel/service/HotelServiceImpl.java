package com.mycompany.hotel.service;

import com.mycompany.hotel.model.Guest;
import com.mycompany.hotel.model.Room;
import com.mycompany.hotel.model.RoomType;
import com.mycompany.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service("hotelServiceImpl")
public class HotelServiceImpl implements HotelService {


    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Map<String, Room> getAllRooms() {
        return hotelRepository.getRooms();
    }


    @Override
    public Map<String, Guest> getAllGuests() {
        return hotelRepository.getGuests();

    }

    @Override
    public Set<Guest> getGuestsWithReservations() throws HotelServiceException {
        Set<Guest> result =
                hotelRepository.getRooms().entrySet().stream()
                        .filter(x -> x.getValue().getGuest() != null)
                        .map(x -> x.getValue().getGuest())
                        .collect(Collectors.toSet());


        if (result.isEmpty()) {
            throw new HotelServiceException("There is no reservations in this hotel");
        } else {
            return result;
        }


    }


    @Override
    public Room getRoomByNumber(String roomNumber) throws HotelServiceException {
        if (hotelRepository.getRooms().containsKey(roomNumber)) {
            return hotelRepository.getRooms().get(roomNumber);
        } else {
            throw new HotelServiceException("The room with number '" + roomNumber + "' doesn't exist");
        }
    }


    @Override
    public Set<Room> getRooms(RoomType type) throws HotelServiceException {
        Set<Room> result = hotelRepository.getRooms().entrySet().stream()
                .filter(x -> x.getValue().getRoomType().equals(type))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

        if (result.isEmpty()) {
            throw new HotelServiceException("There is no rooms of type " + type + " in hotel");
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> getRooms(Guest guest) throws HotelServiceException {

        Set<Room> result = hotelRepository.getRooms().entrySet().stream()
                .filter(x -> guest.equals(x.getValue().getGuest()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());


        if (result.isEmpty()) {
            throw new HotelServiceException("There is no reservations for quest " + guest.getName());
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> getRooms(double fromPrice, double toPrice) throws HotelServiceException {

        Set<Room> result = hotelRepository.getRooms().entrySet().stream()
                .filter(x -> x.getValue().getPrice() >= fromPrice && x.getValue().getPrice() <= toPrice)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());


        if (result.isEmpty()) {
            throw new HotelServiceException("These is no rooms in specified price boundaries");
        } else {
            return result;
        }


    }


    @Override
    public Guest getGuest(String name) throws HotelServiceException {
        if (hotelRepository.getGuests().containsKey(name)) {
            return hotelRepository.getGuests().get(name);
        } else {
            throw new HotelServiceException("The guest with name '" + name + "' doesn't exist");
        }

    }


    @Override
    public Set<Room> getAvailableRooms() throws HotelServiceException {
        Set<Room> result = hotelRepository.getRooms().entrySet().stream()
                .filter(x -> x.getValue().getGuest() == null)
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

        if (result.isEmpty()) {
            throw new HotelServiceException("All rooms are reserved!");
        } else {
            return result;
        }


    }


    @Override
    public Set<Room> getAvailableRooms(RoomType roomType) throws HotelServiceException {
        Set<Room> result = hotelRepository.getRooms().entrySet().stream()
                .filter(x -> x.getValue().getGuest() == null && x.getValue().getRoomType().equals(roomType))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());

        if (result.isEmpty()) {
            throw new HotelServiceException("There is no available rooms of type: " + roomType);
        } else {
            return result;
        }
    }


    @Override
    public boolean reserveRoom(Guest guest, Room room) throws HotelServiceException {
        if (room.getGuest() == null) {
            if (guest.getMoney() >= room.getPrice()) {

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
        if (room.getGuest() != null) {
            if (room.getGuest().equals(guest)) {

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
