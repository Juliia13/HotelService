package com.jaspersoft.hotel.repository;

import com.jaspersoft.hotel.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = AppConfig.class)

public class HotelRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HotelRepository hotelRepository;




    @Test(description = "Verify repository contains guests")
    public void testGetGuests() {
        Assert.assertFalse(hotelRepository.getGuests().isEmpty(), "Repository doesn't contain any guests");
    }

    @Test(description = "Verify repository contains rooms")
    public void testGetRooms() {
        Assert.assertFalse(hotelRepository.getRooms().isEmpty(), "Repository doesn't contain any rooms");
    }

    @Test(description = "Verify each room has a number")
    public void testRoomNumber() {
        hotelRepository.getRooms().forEach(
                (key, value) -> Assert.assertNotNull(value.getRoomNumber())
        );
    }

    @Test(description = "Verify each room has a type")
    public void testRoomType() {
        hotelRepository.getRooms().forEach(
                (key, value) -> Assert.assertNotNull(value.getRoomType())
        );
    }


    @Test(description = "Verify each guest has a name")
    public void testGuestName() {
        hotelRepository.getGuests().forEach(
                (key, value) -> Assert.assertNotNull(value.getName())
        );
    }


}