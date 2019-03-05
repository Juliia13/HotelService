package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.AppConfig;
import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;


@ContextConfiguration(classes = AppConfig.class)
@PropertySource("prices.properties")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestHotelServiceImpl extends AbstractTestNGSpringContextTests {

    @Value("${KING_ROOM}")
    private double KING_ROOM_PRICE;

    @Value("${QUEEN_ROOM}")
    private double QUEEN_ROOM_PRICE;

    @Value("${DOUBLE_QUEEN_ROOM}")
    private double DOUBLE_QUEEN_ROOM_PRICE;

    @Value("${DOUBLE_FULL_ROOM}")
    private double DOUBLE_FULL_ROOM_PRICE;

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

    @DataProvider
    Object[][] priceBoundaries() {
        return new Object[][]{
                {0, 158.75},
                {KING_ROOM_PRICE, QUEEN_ROOM_PRICE}

        };
    }


    @BeforeClass
    public void beforeClassMethod() {
        if (hotelService == null) {
            throw new SkipException("The hotelService is null");
        }
    }


    @Test(description = "Verify service returns all rooms in the hotel")
    public void testShowAllRooms() {
        Assert.assertEquals(hotelService.showAllRooms().size(), 10);

    }

    @Test(description = "Verify service returns all hotel quests")
    public void testShowGuests() {
        Assert.assertEquals(hotelService.showGuests().size(), 5);
    }

    @Test(description = "Verify service returns quests that have reservations")
    public void testShowGuestsWithReservations() throws HotelServiceException {
        SoftAssert softAssert = new SoftAssert();

        HashSet<String> guests = new HashSet<>();
        Collections.addAll(guests, "Bob Smith", "Marry Johnson", "Tom Brown", "Anna Davis");

        softAssert.assertEquals(hotelService.showGuestsWithReservations().size(), 4, "The number of guests is wrong");
        for (Guest guest : hotelService.showGuestsWithReservations()) {
            softAssert.assertTrue(guests.contains(guest.getName()), "The expected set doesn't contain quest with name " + guest.getName());
        }

        softAssert.assertAll();

    }


    @Test(description = "Verify message when there is no reservations in the hotel",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations in this hotel")
    @DirtiesContext
    public void testShowGuestsWithReservations2() throws HotelServiceException {
        // we need to cancel all reservations before this verification
        for (Map.Entry<String, Room> entry : hotelService.showAllRooms().entrySet()) {
            if (!entry.getValue().isAvailable()) {
                hotelService.cancelReservation(entry.getValue().getGuest(), entry.getValue().getRoomNumber());
            }

        }

        hotelService.showGuestsWithReservations();

    }


    @Test(description = "Verify service return room by number")
    public void testShowRoomByNumber() throws HotelServiceException {
        Assert.assertEquals(hotelService.showRoomByNumber("1C").getRoomNumber(), "1C");
    }


    @Test(description = "Verify message when room with specified number doesn't exist",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room with number 'test' doesn't exist")
    public void testShowRoomByNumber2() throws HotelServiceException {
        hotelService.showRoomByNumber("test");

    }


    @Test(description = "Verify service returns rooms of specific type", dataProvider = "roomsByTypes")
    public void testShowRoomByType(RoomType roomType) throws HotelServiceException {
        for (Room room : hotelService.showRooms(roomType)) {
            Assert.assertEquals(room.getRoomType(), roomType);
        }
    }

    @Test(description = "Verify message when there is no rooms of specific type",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no rooms of type TEST in hotel")
    public void testShowRoomByType2() throws HotelServiceException {
        hotelService.showRooms(RoomType.TEST);
    }


    @Test(description = "Verify service returns rooms reserved by specific user")
    public void testShowRoomsReservedByUser() throws HotelServiceException {
        Guest guest = hotelService.showGuests().get("Tom Brown");

        for (Room room : hotelService.showRooms(guest)) {
            Assert.assertEquals(room.getGuest().getName(), "Tom Brown");
        }

    }

    @Test(description = "Verify exception when there is no reservations for user",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations for quest Adam Miller")
    public void testShowRoomsReservedByUser2() throws HotelServiceException {
        hotelService.showRooms(hotelService.showGuests().get("Adam Miller"));
    }


    //what approach to use? current OR verify expected result by rooms numbers?
    @Test(description = "Verify service return rooms that are in specified price boundaries",
            dataProvider = "priceBoundaries")
    public void testShowRoomsByPrice(double test1, double test2) throws HotelServiceException {

        for (Room room : hotelService.showRooms(test1, test2)) {
            Assert.assertTrue(room.getPrice() >= test1 && room.getPrice() <= test2);
        }

    }

    @Test(description = "Verify message when there are no rooms in specified price boundaries",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "These is no rooms in specified price boundaries")
    public void testShowRoomsByPrice2() throws HotelServiceException {
        hotelService.showRooms(0, 5.5);
    }


    @Test(description = "Verify service return guest by name")
    public void testShowGuestByName() throws HotelServiceException {
        Assert.assertEquals(hotelService.showGuest("Marry Johnson").getName(), "Marry Johnson");
    }


    @Test(description = "Verify message when guest with specified name not found",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The guest with name 'Test' doesn't exist")
    public void testShowGuestByName2() throws HotelServiceException {
        hotelService.showGuest("Test");

    }







































    @Test(description = "Verify user can get all available rooms")
    public void testShowAvailableRooms() {

        for (Room room : hotelService.showAvailableRooms()) {
            Assert.assertTrue(room.isAvailable());
        }
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


