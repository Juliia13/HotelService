package com.jaspersoft.repository;

import com.jaspersoft.model.Guest;
import com.jaspersoft.model.Hotel;
import com.jaspersoft.model.Room;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class HotelRepositoryImpl implements HotelRepository {

    ApplicationContext context = new AnnotationConfigApplicationContext(HotelRepositoryConfig.class);


    private Hotel moonstone42;


    public HotelRepositoryImpl() {
        init();

    }


    @Override
    public Hotel init() {

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Guest> quests = new ArrayList<>();

        context.getBeansOfType(Room.class).forEach((name, object) -> {
            rooms.add(object);

        });
        context.getBeansOfType(Guest.class).forEach((name, object) -> {
            quests.add(object);

        });

        moonstone42 = new Hotel(rooms, quests);
        return moonstone42;

    }

    //get hotel instance
    @Override
    public Hotel findAll() {
        return moonstone42;
    }


}
