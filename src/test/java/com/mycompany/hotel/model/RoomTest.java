package com.mycompany.hotel.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoomTest {
    private Room room;

    @BeforeMethod
    public void beforeEachTest() {
        room = new Room();
        room.setRoomNumber("1D");
        room.setRoomType(RoomType.KING_ROOM);
        room.setGuest(null);
        room.setPrice(45.88);
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





    @Test(description = "Verify getters and setters for room guest")
    public void testSetGetGuest() {
        Guest guest = new Guest();
        guest.setName("Bob");
        guest.setMoney(55.5);
        room.setGuest(guest);
        Assert.assertEquals(room.getGuest(), guest);
    }


    @Test(description = "Verify getters and setters for room price")
    public void testSetGetPrice() {
        room.setPrice(154.78);
        Assert.assertEquals(room.getPrice(), 154.78);
    }

    @Test(description = "Verify equals method compares rooms by number")
    public void testEquals() {
        Room room1 = new Room();
        room1.setRoomNumber("1D");
        room1.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room1.setGuest(null);
        room1.setPrice(458.78);
        Assert.assertEquals(room, room1);
    }

    @Test(description = "Verify rooms with different numbers are not equal")
    public void testNotEquals() {
        Room room1 = new Room();
        room1.setRoomNumber("1A");
        room1.setRoomType(RoomType.KING_ROOM);
        room1.setGuest(null);
        room1.setPrice(45.88);
        Assert.assertNotEquals(room, room1);
    }

    @Test(description = "Verify hashcode method takes into account only room number")
    public void testHashCode() {
        Room room1 = new Room();
        room1.setRoomNumber("1D");
        room1.setRoomType(RoomType.DOUBLE_QUEEN_ROOM);
        room1.setGuest(null);
        room1.setPrice(458.78);
        Assert.assertEquals(room.hashCode(), room1.hashCode());
    }
}