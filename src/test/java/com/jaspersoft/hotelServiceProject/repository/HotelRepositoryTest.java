package com.jaspersoft.hotelServiceProject.repository;

import com.jaspersoft.hotelServiceProject.AppConfig;
import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

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
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            Assert.assertNotNull(entry.getValue().getRoomNumber());
        }
    }

    @Test(description = "Verify each room has a type")
    public void testRoomType() {
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            Assert.assertNotNull(entry.getValue().getRoomType());
        }
    }


    @Test(description = "Verify when room is not available, it has a guest")
    public void testNotAvailableRooms() {
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (!entry.getValue().isAvailable()) {
                Assert.assertNotNull(entry.getValue().getGuest());
            }

        }
    }

    @Test(description = "Verify when room is available, it doesn't contain a guest reference")
    public void testAvailableRooms() {
        for (Map.Entry<String, Room> entry : hotelRepository.getRooms().entrySet()) {
            if (entry.getValue().isAvailable()) {
                Assert.assertNull(entry.getValue().getGuest());
            }
        }
    }


    @Test(description = "Verify each guest has a name")
    public void testGuestName() {
        for (Map.Entry<String, Guest> entry : hotelRepository.getGuests().entrySet()) {
            Assert.assertNotNull(entry.getValue().getName());
        }
    }


}