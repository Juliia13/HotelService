package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Hotel;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("hotelRepositoryImpl")
public class HotelRepositoryImpl implements HotelRepository {

    private ApplicationContext context = new AnnotationConfigApplicationContext(HotelRepositoryConfig.class);


    private Hotel moonstone42;

    public HotelRepositoryImpl() {
        this.moonstone42 = new Hotel(showRooms(), showGuests());

    }

    //get hotel instance
    @Override
    public Hotel findAll() {
        return moonstone42;
    }

    @Override
    public ArrayList<Guest> showGuests() {
        return new ArrayList<>(context.getBeansOfType(Guest.class).values());
    }

    @Override
    public ArrayList<Room> showRooms() {
        return new ArrayList<>(context.getBeansOfType(Room.class).values());
    }


}
