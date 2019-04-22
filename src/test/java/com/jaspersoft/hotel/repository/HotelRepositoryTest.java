package com.jaspersoft.hotel.repository;

import com.jaspersoft.hotel.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = AppConfig.class)

public class HotelRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HotelRepository hotelRepository;


    @BeforeClass
    public void beforeClassMethod() {
        if (hotelRepository == null) {
            throw new SkipException("The hotelService is null");
        }
    }


    @Test(description = "Verify repository returns all guests")
    public void testGetGuests() {
        Assert.assertEquals(hotelRepository.getGuests().size(), 5);

    }

    @Test(description = "Verify repository returns all rooms")
    public void testGetRooms() {
        Assert.assertEquals(hotelRepository.getRooms().size(), 11);
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