package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hotelRepositoryImpl")
public class HotelRepositoryImpl implements HotelRepository {

    @Autowired
    private Hotel moonstone42;


    //get hotel instance
    @Override
    public Hotel findAll() {
        return moonstone42;
    }


}
