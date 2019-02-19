package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.model.Hotel;

public interface HotelRepository {

    void init();

    Hotel findAll();
}
