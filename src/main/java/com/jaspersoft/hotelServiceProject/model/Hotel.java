package com.jaspersoft.hotelServiceProject.model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private List<Room> rooms;
    private List<Guest> quests;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Guest> getGuests() {
        return quests;
    }

    public void setGuests(ArrayList<Guest> quests) {
        this.quests = quests;
    }


    public Hotel(List<Room> rooms, List<Guest> quests) {
        this.rooms = rooms;
        this.quests = quests;
    }


    @Override
    public String toString() {
        return "Hotel{\n" +
                "rooms:\n" + rooms +
                ", \nquests:\n" + quests +
                '}';
    }
}
