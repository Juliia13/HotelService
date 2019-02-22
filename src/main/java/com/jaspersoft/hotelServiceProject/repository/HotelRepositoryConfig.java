package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelRepositoryConfig {

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom1() {
        return new Room("1A",
                RoomType.KING_ROOM,
                true,
                null,
                29.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom2() {
        return new Room("2A",
                RoomType.DOUBLE_FULL_ROOM,
                true,
                null,
                119.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom3() {
        return new Room("3A",
                RoomType.QUEEN_ROOM,
                false,
                getBob(),
                19.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom4() {
        return new Room("4A",
                RoomType.DOUBLE_QUEEN_ROOM,
                false,
                getMarry(),
                19.99);
    }


    @Bean
    @Qualifier("moonstone42")
    public Guest getBob() {
        return new Guest("Bob",
                489.55);
    }

    @Bean
    @Qualifier("moonstone42")
    public Guest getMarry() {
        return new Guest("Marry",
                89.55);
    }

    @Bean
    @Qualifier("moonstone42")
    public Guest getTom() {
        return new Guest("Tom",
                29.55);
    }






}
