package com.jaspersoft.hotelServiceProject.model;

import java.util.ArrayList;

public class Hotel {

    private ArrayList<Room> rooms;
    private ArrayList<Guest> quests;

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Guest> getGuests() {
        return quests;
    }

    public void setGuests(ArrayList<Guest> quests) {
        this.quests = quests;
    }


    public Hotel(ArrayList<Room> rooms, ArrayList<Guest> quests) {
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
