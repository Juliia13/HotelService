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


//    @Bean
//    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer () {
//        return new PropertySourcesPlaceholderConfigurer();
//    }


    @Bean
    public double getKING_ROOM_PRICE() {
        return KING_ROOM_PRICE;
    }

    @Bean
    public double getQUEEN_ROOM_PRICE() {
        return QUEEN_ROOM_PRICE;
    }

    @Bean
    public double getDOUBLE_QUEEN_ROOM_PRICE() {
        return DOUBLE_QUEEN_ROOM_PRICE;
    }

    @Bean
    public double getDOUBLE_FULL_ROOM_PRICE() {
        return DOUBLE_FULL_ROOM_PRICE;
    }


    @Bean
    @Qualifier("moonstone42")
    public Room getRoom1() {
        return new Room("1A",
                RoomType.KING_ROOM,
                true,
                null,
                KING_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom2() {
        return new Room("2A",
                RoomType.KING_ROOM,
                false,
                getAnna(),
                KING_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom3() {
        return new Room("1AB",
                RoomType.KING_ROOM,
                true,
                null,
                KING_ROOM_PRICE);
    }


    @Bean
    @Qualifier("moonstone42")
    public Room getRoom4() {
        return new Room("3A",
                RoomType.DOUBLE_FULL_ROOM,
                true,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom5() {
        return new Room("3C",
                RoomType.DOUBLE_FULL_ROOM,
                true,
                null,
                DOUBLE_FULL_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom6() {
        return new Room("3AB",
                RoomType.QUEEN_ROOM,
                false,
                getBob(),
                QUEEN_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom7() {
        return new Room("4A",
                RoomType.QUEEN_ROOM,
                true,
                null,
                QUEEN_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom8() {
        return new Room("4B",
                RoomType.DOUBLE_QUEEN_ROOM,
                false,
                getMarry(),
                DOUBLE_QUEEN_ROOM_PRICE);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom9() {
        return new Room("4C",
                RoomType.DOUBLE_QUEEN_ROOM,
                true,
                null,
                DOUBLE_QUEEN_ROOM_PRICE);
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

    @Bean
    @Qualifier("moonstone42")
    public Guest getAnna() {
        return new Guest("Anna",
                59.55);
    }

    @Bean
    @Qualifier("moonstone42")
    public Guest getAdam() {
        return new Guest("Adam",
                5.55);
    }






}
