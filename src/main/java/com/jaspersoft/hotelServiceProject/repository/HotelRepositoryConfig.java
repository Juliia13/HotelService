package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelRepositoryConfig {

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom1() {
        return new Room("1A",
                "King Room",
                true,
                null,
                29.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom2() {
        return new Room("2A",
                "Queen Room",
                true,
                null,
                119.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom3() {
        return new Room("3A",
                "Double Queen Room",
                false,
                getBob(),
                19.99);
    }

    @Bean
    @Qualifier("moonstone42")
    public Room getRoom4() {
        return new Room("4A",
                "Double Full Room",
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
