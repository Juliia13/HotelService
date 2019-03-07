package com.jaspersoft.hotelServiceProject.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoomTest {
    private Room room;

    @BeforeMethod
    public void beforeEachTest() {
        room = new Room("1D", RoomType.KING_ROOM, true, null, 45.88);
    }

    @Test(description = "Verify getters and setters for roomNumber")
    public void testSetGetRoomNumber() {
        room.setRoomNumber("test");
        Assert.assertEquals(room.getRoomNumber(), "test");
    }

    @Test(description = "Verify getters and setters for roomTYpe")
    public void testSetGetRoomType() {
        room.setRoomType(RoomType.QUEEN_ROOM);
        Assert.assertEquals(room.getRoomType(), RoomType.QUEEN_ROOM);
    }


    @Test(description = "Verify getters and setters for availability")
    public void testGetSetAvailability() {
        room.setAvailable(false);
        Assert.assertFalse(room.isAvailable());
    }


    @Test
    public void testSetGetGuest() {
        Guest guest = new Guest("Bob", 55.5);
        room.setGuest(guest);
        Assert.assertEquals(room.getGuest(), guest);
    }


    @Test
    public void testSetGetPrice() {
        room.setPrice(154.78);
        Assert.assertEquals(room.getPrice(), 154.78);
    }

    @Test
    public void testEquals() {
        Room room1 = new Room("1D", RoomType.DOUBLE_QUEEN_ROOM, true, null, 458.78);
        Assert.assertEquals(room, room1);
    }

    @Test
    public void testHashCode() {
        Room room1 = new Room("1D", RoomType.DOUBLE_QUEEN_ROOM, true, null, 458.78);
        Assert.assertEquals(room.hashCode(), room1.hashCode());
    }
}