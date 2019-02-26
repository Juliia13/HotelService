package com.jaspersoft.hotelServiceProject.service;

import com.jaspersoft.hotelServiceProject.AppConfig;
import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


@ContextConfiguration(classes = AppConfig.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestHotelServiceImpl extends AbstractTestNGSpringContextTests {
    @Autowired
    private HotelService hotelService;

    @DataProvider
    Object[][] roomsByTypes() {
        return new Object[][]{
                {RoomType.KING_ROOM, Arrays.asList(
                        //or better to use getBean()?
                        //or create local variables and use them?
                        new Room("1A",
                                RoomType.KING_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getKING_ROOM_PRICE")),
                        new Room("2A",
                                RoomType.KING_ROOM,
                                false,
                                new Guest("Anna",
                                        59.55),
                                (Double) applicationContext.getBean("getKING_ROOM_PRICE")),
                        new Room("1AB",
                                RoomType.KING_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getKING_ROOM_PRICE"))
                )},
                {RoomType.DOUBLE_FULL_ROOM, Arrays.asList(
                        new Room("3A",
                                RoomType.DOUBLE_FULL_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getDOUBLE_FULL_ROOM_PRICE")),
                        new Room("3C",
                                RoomType.DOUBLE_FULL_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getDOUBLE_FULL_ROOM_PRICE"))


                )},
                {RoomType.QUEEN_ROOM, Arrays.asList(
                        new Room("3AB",
                                RoomType.QUEEN_ROOM,
                                false,
                                new Guest("Bob",
                                        489.55),
                                (Double) applicationContext.getBean("getQUEEN_ROOM_PRICE")),
                        new Room("4A",
                                RoomType.QUEEN_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getQUEEN_ROOM_PRICE"))
                )},
                {RoomType.DOUBLE_QUEEN_ROOM, Arrays.asList(
                        new Room("4B",
                                RoomType.DOUBLE_QUEEN_ROOM,
                                false,
                                new Guest("Marry",
                                        89.55),
                                (Double) applicationContext.getBean("getDOUBLE_QUEEN_ROOM_PRICE")),
                        new Room("4C",
                                RoomType.DOUBLE_QUEEN_ROOM,
                                true,
                                null,
                                (Double) applicationContext.getBean("getDOUBLE_QUEEN_ROOM_PRICE"))
                )}

        };
    }

    @DataProvider
    Object[][] roomsByUser() {
        return new Object[][]{
                {(Guest) applicationContext.getBean("getBob"), Arrays.asList(
                        (Room) applicationContext.getBean("getRoom6")
                )},
                {(Guest) applicationContext.getBean("getAdam"), Arrays.asList()}

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
        Assert.assertEquals(9, hotelService.showAllRooms().size());

    }

    @Test(description = "Verify user can get all quests that are predefined as java spring beans")
    public void testShowGuests() {
        Assert.assertEquals(5, hotelService.showGuests().size());
    }

    @Test(description = "Verify user can get all available rooms")
    public void testShowAvailableRooms() {
        Assert.assertEquals(Arrays.asList(
                new Room("1A", RoomType.KING_ROOM, true, null, (Double) applicationContext.getBean("getKING_ROOM_PRICE")),
                new Room("1AB", RoomType.KING_ROOM, true, null, (Double) applicationContext.getBean("getKING_ROOM_PRICE")),
                new Room("3A", RoomType.DOUBLE_FULL_ROOM, true, null, (Double) applicationContext.getBean("getDOUBLE_FULL_ROOM_PRICE")),
                new Room("3C", RoomType.DOUBLE_FULL_ROOM, true, null, (Double) applicationContext.getBean("getDOUBLE_FULL_ROOM_PRICE")),
                new Room("4A", RoomType.QUEEN_ROOM, true, null, (Double) applicationContext.getBean("getQUEEN_ROOM_PRICE")),
                new Room("4C", RoomType.DOUBLE_QUEEN_ROOM, true, null, (Double) applicationContext.getBean("getDOUBLE_QUEEN_ROOM_PRICE"))
        ), hotelService.showAvailableRooms());


    }

    @Test(description = "Verify user can get all rooms by type", dataProvider = "roomsByTypes")
    public void testShowRoomByType(RoomType roomType, List<Room> rooms) {
        Assert.assertEquals(rooms, hotelService.showRoomByType(roomType));
    }

    @Test(dataProvider = "roomsByUser")
    public void testShowRoomsReservedByUser(Guest guest, List<Room> rooms) {
        Assert.assertEquals(rooms, hotelService.showRoomsReservedByUser(guest));
    }


    @Test
    public void testReserveRoom() {
    }

    @Test
    public void testReserveRoom1() {
    }


    @Test
    public void testCancelReservation() {
    }
}


