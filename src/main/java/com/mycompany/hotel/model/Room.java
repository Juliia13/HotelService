package com.mycompany.hotel.model;

public class Room {

    private String roomNumber;
    private RoomType roomType;
    private Guest guest;
    private double price;

    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        return getRoomNumber() != null ? getRoomNumber().equals(room.getRoomNumber()) : room.getRoomNumber() == null;
    }

    @Override
    public int hashCode() {
        return getRoomNumber() != null ? getRoomNumber().hashCode() : 0;
    }
}
