package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.AppConfig;
import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;


@ContextConfiguration(classes = AppConfig.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestHotelServiceImpl extends AbstractTestNGSpringContextTests {
    @Autowired
    private HotelService hotelService;

    @DataProvider
    Object[][] roomsByTypes() {
        return new Object[][]{
                {RoomType.KING_ROOM},
                {RoomType.DOUBLE_FULL_ROOM},
                {RoomType.QUEEN_ROOM},
                {RoomType.DOUBLE_QUEEN_ROOM}

        };
    }


    @BeforeClass
    public void beforeClassMethod() {
        if (hotelService == null) {
            throw new SkipException("The hotelService is null");
        }
    }


    @Test(description = "Verify user can get all rooms that are predefined as java spring beans")
    public void testShowAllRooms() {
        Assert.assertEquals(hotelService.showAllRooms().size(), 10);

    }

    @Test(description = "Verify user can get all quests that are predefined as java spring beans")
    public void testShowGuests() {
        Assert.assertEquals(hotelService.showGuests().size(), 5);
    }

    @Test(description = "Verify user can get all available rooms")
    public void testShowAvailableRooms() {

        for (Room room : hotelService.showAvailableRooms()) {
            Assert.assertTrue(room.isAvailable());
        }
    }


    @Test(description = "Verify user can get all rooms by type", dataProvider = "roomsByTypes")
    public void testShowRoomByType(RoomType roomType) {
        for (Room room : hotelService.showRoomByType(roomType)) {
            Assert.assertEquals(room.getRoomType(), roomType);
        }
    }


    @Test(description = "Verify service can return rooms reserved by specific user")
    public void testShowRoomsReservedByUser() throws HotelServiceException {
        Guest guest = new Guest("Tom", 29.55);
        ArrayList<Room> rooms = hotelService.showRoomsReservedByUser(guest);
        for (Room room : rooms) {
            Assert.assertEquals(room.getGuest(), guest);

        }
    }

    @Test(expectedExceptions = HotelServiceException.class, expectedExceptionsMessageRegExp = "There is no reservations for quest Test", description = "Verify exception when there is no reservations for user")
    public void testShowRoomsReservedByUser2() throws HotelServiceException {
        hotelService.showRoomsReservedByUser(new Guest("Test", 789.55));
    }


    @Test(expectedExceptions = HotelServiceException.class, expectedExceptionsMessageRegExp = "There is no such room available: 2A")
    public void testReserveRoomByNumber() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get(0), "2A");
    }

    @Test(expectedExceptions = HotelServiceException.class, expectedExceptionsMessageRegExp = "There is not enough money on your account! The room price is 10.0. There is 5.55 on your account")
    public void testReserveRoomByNumber2() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get(4), "1AB");
    }


    @Test(description = "Verify true value is returned when reservation by room number successful")
    @DirtiesContext
    public void testReserveRoomByNumber3() throws HotelServiceException {
        Assert.assertTrue(hotelService.reserveRoomByNumber(hotelService.showGuests().get(0), "4A"));

    }

    @Test(description = "Verify user money after successful reservation by room number")
    @DirtiesContext
    public void testReserveRoomByNumber4() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get(0), "4A");
        Assert.assertEquals(hotelService.showGuests().get(0).getMoney(), 379.0);
    }


    //is it okay to use getBean in test method? or it's better to create one more hotelService method
    @Test(description = "Verify room status changes when reservation by room number is successful")
    @DirtiesContext
    public void testReserveRoomByNumber5() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get(2), "1AB");
        Room room = (Room) applicationContext.getBean("getRoom3");
        Assert.assertFalse(room.isAvailable());
    }


    @Test
    public void testReserveRoomByType() {
    }


    @Test
    public void testCancelReservation() {
    }


}


