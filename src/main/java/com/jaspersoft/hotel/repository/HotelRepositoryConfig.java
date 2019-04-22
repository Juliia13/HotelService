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
        Room room = new Room();
        room.setRoomNumber("1A");
        room.setRoomType(RoomType.KING_ROOM);
        room.setGuest(null);
        room.setPrice(KING_ROOM_PRICE);
        return room;
    }

    @Bean(name = "1B")
    @Qualifier("moonstone42")
    public Room getRoom2() {
        Room room = new Room();
        room.setRoomNumber("1B");
        room.setRoomType(RoomType.KING_ROOM);
        room.setGuest(getAnna());
        room.setPrice(KING_ROOM_PRICE);
        return room;


    }

    @Bean(name = "1C")
    @Qualifier("moonstone42")
    public Room getRoom3() {
        Room room = new Room();
        room.setRoomNumber("1C");
        room.setRoomType(RoomType.KING_ROOM);
        room.setGuest(null);
        room.setPrice(KING_ROOM_PRICE);
        return room;


    }


    @Bean(name = "2A")
    @Qualifier("moonstone42")
    public Room getRoom4() {
        Room room = new Room();
        room.setRoomNumber("2A");
        room.setRoomType(RoomType.DOUBLE_FULL_ROOM);
        room.setGuest(null);
        room.setPrice(DOUBLE_FULL_ROOM_PRICE);
        return room;


    }

    @Bean(name = "2B")
    @Qualifier("moonstone42")
    public Room getRoom5() {
        Room room = new Room();
        room.setRoomNumber("2B");
        room.setRoomType(RoomType.DOUBLE_FULL_ROOM);
        room.setGuest(null);
        room.setPrice(DOUBLE_FULL_ROOM_PRICE);
        return room;

    }

    @Bean(name = "3A")
    @Qualifier("moonstone42")
    public Room getRoom6() {
        Room room = new Room();
        room.setRoomNumber("3A");
        room.setRoomType(RoomType.QUEEN_ROOM);
        room.setGuest(getBob());
        room.setPrice(QUEEN_ROOM_PRICE);
        return room;


    }

    @Bean(name = "3B")
    @Qualifier("moonstone42")
    public Room getRoom7() {
        Room room = new Room();
        room.setRoomNumber("3B");
        room.setRoomType(RoomType.QUEEN_ROOM);
        room.setGuest(null);
        room.setPrice(QUEEN_ROOM_PRICE);
        return room;


    }

    @Bean(name = "4A")
    @Qualifier("moonstone42")
    public Room getRoom8() {
        Room room = new Room();
        room.setRoomNumber("4A");
        room.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room.setGuest(getMarry());
        room.setPrice(DOUBLE_QUEEN_ROOM_PRICE);
        return room;


    }

    @Bean(name = "4B")
    @Qualifier("moonstone42")
    public Room getRoom9() {
        Room room = new Room();
        room.setRoomNumber("4B");
        room.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room.setGuest(getTom());
        room.setPrice(DOUBLE_QUEEN_ROOM_PRICE);
        return room;


    }

    @Bean(name = "4C")
    @Qualifier("moonstone42")
    public Room getRoom10() {
        Room room = new Room();
        room.setRoomNumber("4C");
        room.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room.setGuest(getTom());
        room.setPrice(DOUBLE_QUEEN_ROOM_PRICE);
        return room;

    }

    @Bean(name = "5A")
    @Qualifier("moonstone42")
    public Room getRoom11() {
        Room room = new Room();
        room.setRoomNumber("5A");
        room.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room.setGuest(null);
        room.setPrice(DOUBLE_QUEEN_ROOM_PRICE);
        return room;
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
