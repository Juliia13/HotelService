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


    @Test(expectedExceptions = HotelServiceException.class, expectedExceptionsMessageRegExp = "There is no such room available: 1B")
    public void testReserveRoomByNumber() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get("Bob Smith"), "1B");
    }

    @Test(expectedExceptions = HotelServiceException.class, expectedExceptionsMessageRegExp = "There is not enough money on your account! The room price is 10.0. There is 5.55 on your account")
    public void testReserveRoomByNumber2() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get("Adam Miller"), "1C");
    }


    @Test(description = "Verify true value is returned when reservation by room number successful")
    @DirtiesContext
    public void testReserveRoomByNumber3() throws HotelServiceException {
        Assert.assertTrue(hotelService.reserveRoomByNumber(hotelService.showGuests().get("Bob Smith"), "3B"));

    }

    @Test(description = "Verify user money after successful reservation by room number")
    @DirtiesContext
    public void testReserveRoomByNumber4() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get("Bob Smith"), "3B");
        Assert.assertEquals(hotelService.showGuests().get("Bob Smith").getMoney(), 379.0);
    }


    //is it okay to use getBean in test method? or it's better to create one more hotelService method
    @Test(description = "Verify room status changes when reservation by room number is successful")
    @DirtiesContext
    public void testReserveRoomByNumber5() throws HotelServiceException {
        hotelService.reserveRoomByNumber(hotelService.showGuests().get("Tom Brown"), "1C");
        Room room = (Room) applicationContext.getBean("1C");
        Assert.assertFalse(room.isAvailable());
    }


    @Test
    public void testReserveRoomByType() {
    }


    @Test
    public void testCancelReservation() {
    }


    //test search logic
     /*
       Verify service return all rooms in the hotel
       Verify service return all hotel customers
       Verify service return customers that have reservations

       ?maybe overload
       Verify service return rooms of specific type
       Verify service return room by number
       Verify service return rooms that reserved by specific user --> +exc caseS
       Verify service returns rooms that are in specified price boundaries

       Verify service return quest by name

       ?maybe overload
       Verify service return all available rooms
       Verify service returns all available rooms of specific type
       Verify service returns all available rooms of specific type and in specified price boundaries (2 cases from this)

       ?Verify service checks room availability by room number

       Verify each room has a room number
       ?Verify each room has a price, type
       Verify each roomNumber is unique
       Verify each customer has a name
       Verify each name is unique


      //reservation logic
        //when successful (soft assert)
       Verify service can make a reservation for specific quest (returns true)
       Verify correct amount of money are substracted from quest when reservation is successful
       Verify room status is changed when room is reserved
       Verify room contains link to quest after successful reservation

        //when fail
       Verify validation when user don't have enough money for reservation
       Verify validation when specified room is not available for reservation

       //cancel reserve logic
        Verify service can cancel reservation for specific quest (returns true)
        Verify money are added to user account after reservation cancelled
        Verify room status after cancel reservation
        Verify room quest field is null when cancel reservation
        Verify validation when user didn't reserve specified room
        Verify money are not added to user account when cancel operation is not success


       Verify message when room not found (for all search service tests)


      */







}


