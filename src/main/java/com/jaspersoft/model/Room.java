package com.jaspersoft.model;

import java.util.Objects;

public class Room {

    private String roomNumber;
    private String roomType;
    private boolean available;
    private Guest guest;
    private double price;

    public Room(String roomNumber, String roomType, boolean available, Guest guest, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.available = available;
        this.guest = guest;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
        return isAvailable() == room.isAvailable() &&
                Double.compare(room.getPrice(), getPrice()) == 0 &&
                getRoomNumber().equals(room.getRoomNumber()) &&
                getRoomType().equals(room.getRoomType()) &&
                getGuest().equals(room.getGuest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getRoomType(), isAvailable(), getGuest(), getPrice());
    }


    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomType='" + roomType + '\'' +
                ", available=" + available +
                ", guest=" + guest +
                ", price=" + price +
                '}';
    }
}
