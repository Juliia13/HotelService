package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("prices.properties")
public class HotelRepositoryConfig {

    @Value("${KING_ROOM}")
    private double KING_ROOM_PRICE;

    @Value("${QUEEN_ROOM}")
    private double QUEEN_ROOM_PRICE;

    @Value("${DOUBLE_QUEEN_ROOM}")
    private double DOUBLE_QUEEN_ROOM_PRICE;

    @Value("${DOUBLE_FULL_ROOM}")
    private double DOUBLE_FULL_ROOM_PRICE;


    @Bean(name = "1A")
    @Qualifier("moonstone42")
    public Room getRoom1() {
        return new Room("1A",
                RoomType.KING_ROOM,
                true,
                null,
                KING_ROOM_PRICE);
    }

    @Bean(name = "1B")
    @Qualifier("moonstone42")
    public Room getRoom2() {
        return new Room("1B",
                RoomType.KING_ROOM,
                false,
                getAnna(),
                KING_ROOM_PRICE);
    }

    @Bean(name = "1C")
    @Qualifier("moonstone42")
    public Room getRoom3() {
        return new Room("1C",
                RoomType.KING_ROOM,
                true,
                null,
                KING_ROOM_PRICE);
    }


    @Bean(name = "2A")
    @Qualifier("moonstone42")
    public Room getRoom4() {
        return new Room("2A",
                RoomType.DOUBLE_FULL_ROOM,
                true,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean(name = "2B")
    @Qualifier("moonstone42")
    public Room getRoom5() {
        return new Room("2B",
                RoomType.DOUBLE_FULL_ROOM,
                true,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean(name = "3A")
    @Qualifier("moonstone42")
    public Room getRoom6() {
        return new Room("3A",
                RoomType.QUEEN_ROOM,
                false,
                getBob(),
                QUEEN_ROOM_PRICE);
    }

    @Bean(name = "3B")
    @Qualifier("moonstone42")
    public Room getRoom7() {
        return new Room("3B",
                RoomType.QUEEN_ROOM,
                true,
                null,
                QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4A")
    @Qualifier("moonstone42")
    public Room getRoom8() {
        return new Room("4A",
                RoomType.DOUBLE_QUEEN_ROOM,
                false,
                getMarry(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4B")
    @Qualifier("moonstone42")
    public Room getRoom9() {
        return new Room("4B",
                RoomType.DOUBLE_QUEEN_ROOM,
                false,
                getTom(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4C")
    @Qualifier("moonstone42")
    public Room getRoom10() {
        return new Room("4C",
                RoomType.DOUBLE_QUEEN_ROOM,
                false,
                getTom(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "5A")
    @Qualifier("moonstone42")
    public Room getRoom11() {
        return new Room("5A",
                RoomType.DOUBLE_QUEEN_ROOM,
                true,
                null,
                DOUBLE_QUEEN_ROOM_PRICE);
    }


    @Bean(name = "Bob Smith")
    @Qualifier("moonstone42")
    public Guest getBob() {
        return new Guest("Bob Smith",
                489.55);
    }

    @Bean(name = "Marry Johnson")
    @Qualifier("moonstone42")
    public Guest getMarry() {
        return new Guest("Marry Johnson",
                89.55);
    }

    @Bean(name = "Tom Brown")
    @Qualifier("moonstone42")
    public Guest getTom() {
        return new Guest("Tom Brown",
                29.55);
    }

    @Bean(name = "Anna Davis")
    @Qualifier("moonstone42")
    public Guest getAnna() {
        return new Guest("Anna Davis",
                59.55);
    }

    @Bean(name = "Adam Miller")
    @Qualifier("moonstone42")
    public Guest getAdam() {
        return new Guest("Adam Miller",
                5.55);
    }






}
