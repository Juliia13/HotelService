package com.jaspersoft.hotel.repository;

import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;
import com.jaspersoft.hotel.model.RoomType;
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

                null,
                KING_ROOM_PRICE);
    }

    @Bean(name = "1B")
    @Qualifier("moonstone42")
    public Room getRoom2() {
        return new Room("1B",
                RoomType.KING_ROOM,
                getAnna(),
                KING_ROOM_PRICE);
    }

    @Bean(name = "1C")
    @Qualifier("moonstone42")
    public Room getRoom3() {
        return new Room("1C",
                RoomType.KING_ROOM,
                null,
                KING_ROOM_PRICE);
    }


    @Bean(name = "2A")
    @Qualifier("moonstone42")
    public Room getRoom4() {
        return new Room("2A",
                RoomType.DOUBLE_FULL_ROOM,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean(name = "2B")
    @Qualifier("moonstone42")
    public Room getRoom5() {
        return new Room("2B",
                RoomType.DOUBLE_FULL_ROOM,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean(name = "3A")
    @Qualifier("moonstone42")
    public Room getRoom6() {
        return new Room("3A",
                RoomType.QUEEN_ROOM,
                getBob(),
                QUEEN_ROOM_PRICE);
    }

    @Bean(name = "3B")
    @Qualifier("moonstone42")
    public Room getRoom7() {
        return new Room("3B",
                RoomType.QUEEN_ROOM,
                null,
                QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4A")
    @Qualifier("moonstone42")
    public Room getRoom8() {
        return new Room("4A",
                RoomType.DOUBLE_QUEEN_ROOM,
                getMarry(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4B")
    @Qualifier("moonstone42")
    public Room getRoom9() {
        return new Room("4B",
                RoomType.DOUBLE_QUEEN_ROOM,
                getTom(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "4C")
    @Qualifier("moonstone42")
    public Room getRoom10() {
        return new Room("4C",
                RoomType.DOUBLE_QUEEN_ROOM,
                getTom(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean(name = "5A")
    @Qualifier("moonstone42")
    public Room getRoom11() {
        return new Room("5A",
                RoomType.DOUBLE_QUEEN_ROOM,
                null,
                DOUBLE_QUEEN_ROOM_PRICE);
    }


    @Bean(name = "Bob Smith")
    @Qualifier("moonstone42")
    public Guest getBob() {
        Guest guest = new Guest();
        guest.setName("Bob Smith");
        guest.setMoney(489.55);
        return guest;
    }

    @Bean(name = "Marry Johnson")
    @Qualifier("moonstone42")
    public Guest getMarry() {
        Guest guest = new Guest();
        guest.setName("Marry Johnson");
        guest.setMoney(89.55);
        return guest;
    }

    @Bean(name = "Tom Brown")
    @Qualifier("moonstone42")
    public Guest getTom() {
        Guest guest = new Guest();
        guest.setName("Tom Brown");
        guest.setMoney(29.55);
        return guest;


    }

    @Bean(name = "Anna Davis")
    @Qualifier("moonstone42")
    public Guest getAnna() {
        Guest guest = new Guest();
        guest.setName("Anna Davis");
        guest.setMoney(59.55);
        return guest;

    }

    @Bean(name = "Adam Miller")
    @Qualifier("moonstone42")
    public Guest getAdam() {
        Guest guest = new Guest();
        guest.setName("Adam Miller");
        guest.setMoney(5.55);
        return guest;

    }


}
