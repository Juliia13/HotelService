package com.jaspersoft.hotel.service;

import com.jaspersoft.hotel.AppConfig;
import com.jaspersoft.hotel.model.Guest;
import com.jaspersoft.hotel.model.Room;
import com.jaspersoft.hotel.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
                {150.55, 530.89},
                {KING_ROOM_PRICE, QUEEN_ROOM_PRICE}

        };
    }




    @Test(description = "Verify service returns all rooms in the hotel")
    public void testGetAllRooms() {
        List<String> roomNumbers = new ArrayList<>();
        hotelService.getAllRooms().forEach((key, value) -> roomNumbers.add(value.getRoomNumber()));

        Assert.assertEquals(roomNumbers, Arrays.asList("1A", "1B", "1C", "2A", "2B", "3A", "3B", "4A", "4B", "4C", "5A"));
    }

    @Test(description = "Verify service returns all quests in the hotel")
    public void testGetAllGuests() {
        List<String> names = new ArrayList<>();
        hotelService.getAllGuests().forEach((key, value) -> names.add(value.getName()));

        Assert.assertEquals(names, Arrays.asList("Bob Smith", "Marry Johnson", "Tom Brown", "Anna Davis", "Adam Miller"));
    }


    @Test(description = "Verify service returns quests that have reservations")
    public void testShowGuestsWithReservations() throws HotelServiceException {
        Set<Guest> guests = hotelService.getGuestsWithReservations();
        Set<String> names = new HashSet<>();
        guests.forEach(guest -> names.add(guest.getName()));
        Assert.assertTrue(names.size() == 4 && names.containsAll(Arrays.asList("Bob Smith", "Marry Johnson", "Tom Brown", "Anna Davis")), "The quests with reservations list has wrong size or contains wrong guests");
    }


    @Test(description = "Verify message when there is no reservations in the hotel",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations in this hotel")
    @DirtiesContext
    public void testShowGuestsWithReservations2() throws HotelServiceException {
        hotelService.getAllRooms().forEach(
                (key, value) -> {
                    if (value.getGuest() != null) {
                        value.setGuest(null);
                    }

                }
        );


        hotelService.getGuestsWithReservations();

    }


    @Test(description = "Verify service return room by number")
    public void testShowRoomByNumber() throws HotelServiceException {
        Assert.assertEquals(hotelService.getRoomByNumber("1C").getRoomNumber(), "1C", "Show room by number gives wrong result");
    }


    @Test(description = "Verify message when room with specified number doesn't exist",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room with number 'test' doesn't exist")
    public void testShowRoomByNumber2() throws HotelServiceException {
        hotelService.getRoomByNumber("test");

    }


    @Test(description = "Verify service returns rooms of specific type", dataProvider = "roomsByTypes")
    public void testShowRoomByType(RoomType roomType) throws HotelServiceException {

        hotelService.getRooms(roomType).forEach(
                (x -> Assert.assertEquals(x.getRoomType(), roomType, "Show rooms by type gives wrong result"))
        );

    }

    @Test(description = "Verify message when there is no rooms of specific type",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no rooms of type DELUXE_DOUBLE in hotel")
    public void testShowRoomByType2() throws HotelServiceException {
        hotelService.getRooms(RoomType.DELUXE_DOUBLE);
    }


    @Test(description = "Verify service returns rooms reserved by specific user")
    public void testShowRoomsReservedByUser() throws HotelServiceException {
        Guest guest = hotelService.getAllGuests().get("Tom Brown");
        hotelService.getRooms(guest).forEach(
                x -> Assert.assertEquals(x.getGuest().getName(), "Tom Brown", "Show rooms by specific user gives wrong result")
        );


    }

    @Test(description = "Verify exception when there is no reservations for user",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no reservations for quest Adam Miller")
    public void testShowRoomsReservedByUser2() throws HotelServiceException {
        hotelService.getRooms(hotelService.getAllGuests().get("Adam Miller"));
    }


    @Test(description = "Verify service return rooms that are in specified price boundaries",
            dataProvider = "priceBoundaries")
    public void testShowRoomsByPrice(double test1, double test2) throws HotelServiceException {
        hotelService.getRooms(test1, test2).forEach(
                x -> Assert.assertTrue(x.getPrice() >= test1 && x.getPrice() <= test2, "Show rooms by price gives wrong result")
        );
    }

    @Test(description = "Verify message when there are no rooms in specified price boundaries",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "These is no rooms in specified price boundaries")
    public void testShowRoomsByPrice2() throws HotelServiceException {
        hotelService.getRooms(0, 5.5);
    }


    @Test(description = "Verify service return guest by name")
    public void testShowGuestByName() throws HotelServiceException {
        Assert.assertEquals(hotelService.getGuest("Marry Johnson").getName(), "Marry Johnson");
    }


    @Test(description = "Verify message when guest with specified name not found",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The guest with name 'Test' doesn't exist")
    public void testShowGuestByName2() throws HotelServiceException {
        hotelService.getGuest("Test");

    }


    @Test(description = "Verify service returns all available rooms")
    public void testShowAvailableRooms() throws HotelServiceException {
        hotelService.getAvailableRooms().forEach(
                x -> Assert.assertNull(x.getGuest())

        );

    }

    @Test(description = "Verify message when there is no available rooms returns all available rooms",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "All rooms are reserved!")
    @DirtiesContext
    public void testShowAvailableRooms2() throws HotelServiceException {
        hotelService.getAllRooms().forEach((key, value) -> {
            if (value.getGuest() == null) {
                value.setGuest(new Guest());
            }
        });

        hotelService.getAvailableRooms();
    }

    @Test(description = "Verify service returns all available rooms of specific type",
            dataProvider = "roomsByTypes")
    public void testShowAvailableRoomsByType(RoomType roomType) throws HotelServiceException {
        hotelService.getAvailableRooms(roomType).forEach(
                x -> Assert.assertTrue(x.getGuest() == null && x.getRoomType().equals(roomType))
        );


    }

    @Test(description = "Verify message when there is no available rooms of specific type",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is no available rooms of type: KING_ROOM")
    @DirtiesContext
    public void testShowAvailableRoomsByType2() throws HotelServiceException {
        hotelService.getRooms(RoomType.KING_ROOM).forEach(
                room -> {
                    if (room.getGuest() == null) {
                        room.setGuest(new Guest());
                    }
                }
        );


        hotelService.getAvailableRooms(RoomType.KING_ROOM);

    }


    @Test(description = "Verify service can make a reservation for specific guest")
    @DirtiesContext
    public void testReserveRoom() throws HotelServiceException {
        Guest guest = hotelService.getAllGuests().get("Bob Smith");
        Room room = hotelService.getRoomByNumber("1C");
        double money = guest.getMoney();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(hotelService.reserveRoom(guest, room), "Reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), money - room.getPrice(), "Wrong amount of money on users's account");
        softAssert.assertNotNull(room.getGuest(), "Wrong rooms status");
        softAssert.assertEquals(room.getGuest(), guest, "Guest is not set to room");
        softAssert.assertAll();
    }


    @Test(description = "Verify service can make a reservation for specific guest when amount of guest money is the same as room price")
    @DirtiesContext
    public void testReserveRoom2() throws HotelServiceException {
        Guest guest = hotelService.getAllGuests().get("Bob Smith");
        Room room = hotelService.getRoomByNumber("1C");
        guest.setMoney(room.getPrice());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(hotelService.reserveRoom(guest, room), "Reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), 0.0, "Wrong amount of money on users's account");
        softAssert.assertNotNull(room.getGuest(), "Wrong rooms status");
        softAssert.assertEquals(room.getGuest(), guest, "Guest is not set to room");
        softAssert.assertAll();
    }


    @Test(description = "Verify validation when user don't have enough money for reservation",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "There is not enough money on your account")
    public void testReserveRoom3() throws HotelServiceException {
        hotelService.reserveRoom(hotelService.getAllGuests().get("Adam Miller"), hotelService.getAllRooms().get("1C"));
    }

    @Test(description = "Verify validation when specified room is not available for reservation",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room is not available")
    public void testReserveRoom4() throws HotelServiceException {
        hotelService.reserveRoom(hotelService.getAllGuests().get("Bob Smith"), hotelService.getAllRooms().get("4B"));
    }

    @Test(description = "Verify service can cancel reservation for specific quest")
    @DirtiesContext
    public void cancelReservation() throws HotelServiceException {
        SoftAssert softAssert = new SoftAssert();
        Guest guest = hotelService.getAllGuests().get("Tom Brown");
        Room room = hotelService.getAllRooms().get("4B");
        double money = guest.getMoney();

        softAssert.assertTrue(hotelService.cancelReservation(guest, room), "Cancel reservation method didn't return true");
        softAssert.assertEquals(guest.getMoney(), money + DOUBLE_QUEEN_ROOM_PRICE, "Wrong amount of money on users's account");
        softAssert.assertNull(room.getGuest(), "Wrong rooms status");
        softAssert.assertNull(room.getGuest(), "Room still has link to user after cancelling reservation");

        softAssert.assertAll();
    }

    @Test(description = "Verify validation when cancel reservation on already available room",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The room is already available! No reservations for specified room found")
    public void cancelReservation2() throws HotelServiceException {
        hotelService.cancelReservation(hotelService.getAllGuests().get("Tom Brown"), hotelService.getAllRooms().get("1C"));

    }

    @Test(description = "Verify validation when cancel reservation by user that didn't book specified room",
            expectedExceptions = HotelServiceException.class,
            expectedExceptionsMessageRegExp = "The guest Tom Brown has no reservation for room 1B")
    public void cancelReservation3() throws HotelServiceException {
        hotelService.cancelReservation(hotelService.getAllGuests().get("Tom Brown"), hotelService.getAllRooms().get("1B"));

    }


}


