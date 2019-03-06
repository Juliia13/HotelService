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

import java.util.Map;
import java.util.Set;


@ContextConfiguration(classes = AppConfig.class)
@PropertySource("prices.properties")
public class HotelServiceTest extends AbstractTestNGSpringContextTests {

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
        Assert.assertEquals(hotelService.showAllRooms().size(), 11);

    }

    @Test(description = "Verify service returns all hotel quests")
    public void testShowGuests() {
        Assert.assertEquals(hotelService.showAllGuests().size(), 5);
    }

    @Test(description = "Verify service returns quests that have reservations")
    public void testShowGuestsWithReservations() throws HotelServiceException {
        Set<Guest> guests = hotelService.showGuestsWithReservations();
        Assert.assertTrue(!guests.isEmpty(), "There are no guests with reservations");
        for (Map.Entry<String, Room> entry : hotelService.showAllRooms().entrySet()) {
            if (!entry.getValue().isAvailable()) {
                guests.remove(entry.getValue().getGuest());
            }
        }

        Assert.assertTrue(guests.isEmpty());

    }


    @Test(description = "Verify message when there is no reservations in the hotel",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations in this hotel")
    @DirtiesContext
    public void testShowGuestsWithReservations2() throws HotelServiceException {
        // we need to cancel all reservations before this verification
        for (Map.Entry<String, Room> entry : hotelService.showAllRooms().entrySet()) {
            if (!entry.getValue().isAvailable()) {
                hotelService.cancelReservation(entry.getValue().getGuest(), entry.getValue());
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
        Guest guest = hotelService.showAllGuests().get("Tom Brown");

        for (Room room : hotelService.showRooms(guest)) {
            Assert.assertEquals(room.getGuest().getName(), "Tom Brown");
        }

    }

    @Test(description = "Verify exception when there is no reservations for user",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations for quest Adam Miller")
    public void testShowRoomsReservedByUser2() throws HotelServiceException {
        hotelService.showRooms(hotelService.showAllGuests().get("Adam Miller"));
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


    @Test(description = "Verify service returns all available rooms")
    public void testShowAvailableRooms() throws HotelServiceException {

        for (Room room : hotelService.showAvailableRooms()) {
            Assert.assertTrue(room.isAvailable());
        }
    }

    @Test(description = "Verify message when there is no available rooms returns all available rooms",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "All rooms are reserved!")
    @DirtiesContext
    public void testShowAvailableRooms2() throws HotelServiceException {
        //we need to reserve all rooms before verification
        for (Map.Entry<String, Room> entry : hotelService.showAllRooms().entrySet()) {
            if (entry.getValue().isAvailable()) {
                entry.getValue().setAvailable(false);
            }
        }

        hotelService.showAvailableRooms();
    }

    @Test(description = "Verify service returns all available rooms of specific type",
            dataProvider = "roomsByTypes")
    public void testShowAvailableRoomsByType(RoomType roomType) throws HotelServiceException {

        for (Room room : hotelService.showAvailableRooms(roomType)) {
            Assert.assertTrue(room.isAvailable() && room.getRoomType().equals(roomType));
        }

    }

    @Test(description = "Verify message when there is no available rooms of specific type",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no available rooms of type: KING_ROOM")
    @DirtiesContext
    public void testShowAvailableRoomsByType2() throws HotelServiceException {
        // we need to book all rooms of tested type
        for (Room room : hotelService.showRooms(RoomType.KING_ROOM)) {
            if (room.isAvailable()) {
                room.setAvailable(false);
            }
        }

        hotelService.showAvailableRooms(RoomType.KING_ROOM);

    }


    @Test(description = "Verify service can make a reservation for specific guest")
    @DirtiesContext
    public void testReserveRoom() throws HotelServiceException {
        Guest guest = hotelService.showAllGuests().get("Bob Smith");
        Room room = hotelService.showRoomByNumber("1C");
        double money = guest.getMoney();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(hotelService.reserveRoom(guest, room), "Reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), money - room.getPrice(), "Wrong amount of money on users's account");
        softAssert.assertFalse(room.isAvailable(), "Wrong rooms status");
        softAssert.assertEquals(room.getGuest(), guest, "Guest is not set to room");
        softAssert.assertAll();
    }


    //boundary test
    @Test(description = "Verify service can make a reservation for specific guest when amount of guest money is the same as room price")
    @DirtiesContext
    public void testReserveRoom2() throws HotelServiceException {
        Guest guest = hotelService.showAllGuests().get("Bob Smith");
        Room room = hotelService.showRoomByNumber("1C");
        guest.setMoney(room.getPrice());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(hotelService.reserveRoom(guest, room), "Reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), 0.0, "Wrong amount of money on users's account");
        softAssert.assertFalse(room.isAvailable(), "Wrong rooms status");
        softAssert.assertEquals(room.getGuest(), guest, "Guest is not set to room");
        softAssert.assertAll();
    }


    @Test(description = "Verify validation when user don't have enough money for reservation",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is not enough money on your account")
    public void testReserveRoom3() throws HotelServiceException {
        hotelService.reserveRoom(hotelService.showAllGuests().get("Adam Miller"), hotelService.showRoomByNumber("1C"));
    }

    @Test(description = "Verify validation when specified room is not available for reservation",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room is not available")
    public void testReserveRoom4() throws HotelServiceException {
        hotelService.reserveRoom(hotelService.showAllGuests().get("Bob Smith"), hotelService.showRoomByNumber("4B"));
    }

    @Test(description = "Verify service can cancel reservation for specific quest")
    @DirtiesContext
    public void cancelReservation() throws HotelServiceException {
        SoftAssert softAssert = new SoftAssert();
        Guest guest = hotelService.showAllGuests().get("Tom Brown");
        Room room = hotelService.showAllRooms().get("4B");
        double money = guest.getMoney();

        softAssert.assertTrue(hotelService.cancelReservation(guest, room), "Cancel reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), money + DOUBLE_QUEEN_ROOM_PRICE, "Wrong amount of money on users's account");
        softAssert.assertTrue(room.isAvailable(), "Wrong rooms status");
        softAssert.assertNull(room.getGuest(), "Room still has link to user after cancelling reservation");

        softAssert.assertAll();
    }

    @Test(description = "Verify validation when cancel reservation on already available room",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room is already available! No reservations for specified room found")
    public void cancelReservation2() throws HotelServiceException {
        hotelService.cancelReservation(hotelService.showAllGuests().get("Tom Brown"), hotelService.showAllRooms().get("1C"));

    }

    @Test(description = "Verify validation when cancel reservation by user that didn't book specified room",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The guest Tom Brown has no reservation for room 1B")
    public void cancelReservation3() throws HotelServiceException {
        hotelService.cancelReservation(hotelService.showAllGuests().get("Tom Brown"), hotelService.showAllRooms().get("1B"));

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


