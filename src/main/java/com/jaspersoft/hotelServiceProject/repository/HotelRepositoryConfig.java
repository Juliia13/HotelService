package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class HotelRepositoryConfig {

    @Bean
    public Room getRoom1() {
        return new Room("1A",
                "King Room",
                true,
                null,
                29.99);
    }

    @Bean
    public Room getRoom2() {
        return new Room("2A",
                "Queen Room",
                true,
                null,
                59.99);
    }

    @Bean
    public Room getRoom3() {
        return new Room("3A",
                "Double Queen Room",
                false,
                getBob(),
                19.99);
    }

    @Bean
    public Room getRoom4() {
        return new Room("4A",
                "Double Full Room",
                false,
                getMarry(),
                19.99);
    }


    @Bean
    public Guest getBob() {
        return new Guest("Bob",
                489.55);
    }

    @Bean
    public Guest getMarry() {
        return new Guest("Marry",
                89.55);
    }

    @Bean
    public Guest getTom() {
        return new Guest("Tom",
                29.55);
    }

    @Bean
    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(getRoom1());
        rooms.add(getRoom2());
        rooms.add(getRoom3());
        rooms.add(getRoom4());
        return rooms;
    }

    @Bean
    public ArrayList<Guest> getGuests() {
        ArrayList<Guest> quests = new ArrayList<>();
        quests.add(getBob());
        quests.add(getTom());
        quests.add(getMarry());
        return quests;
    }

    @Bean
    public Hotel moonstone42() {
        return new Hotel(getRooms(), getGuests());
    }





}
