package com.jaspersoft.hotelServiceProject;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.model.RoomType;
import com.jaspersoft.hotelServiceProject.service.HotelServiceException;
import com.jaspersoft.hotelServiceProject.service.HotelServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HotelServiceImpl hotelServiceImpl = context.getBean("hotelServiceImpl", HotelServiceImpl.class);


        System.out.println("-----------------------------------------");


        System.out.println("when no enough money on account");
        try {
            System.out.println(hotelServiceImpl.reserveRoomByType(hotelServiceImpl.showGuests().get(4), RoomType.KING_ROOM));
        } catch (HotelServiceException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("when okay");
        try {
            System.out.println(hotelServiceImpl.reserveRoomByType(hotelServiceImpl.showGuests().get(0), RoomType.QUEEN_ROOM));
            System.out.println(hotelServiceImpl.showRoomByType(RoomType.QUEEN_ROOM));
        } catch (HotelServiceException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("when no available");
        try {
            System.out.println(hotelServiceImpl.reserveRoomByType(hotelServiceImpl.showGuests().get(0), RoomType.QUEEN_ROOM));
            System.out.println(hotelServiceImpl.showRoomByType(RoomType.QUEEN_ROOM));
        } catch (HotelServiceException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("-----------------------------------------");


        // show all rooms
        System.out.println("All rooms: ");
        for (Room room : hotelServiceImpl.showAllRooms()) {
            System.out.println(room);
        }
        System.out.println("\n");


        //show all guests
        System.out.println("All guests: ");
        for (Guest guest : hotelServiceImpl.showGuests()) {
            System.out.println(guest);
        }
        System.out.println("\n");

        //show available rooms
        System.out.println("Available rooms: ");
        for (Room room : hotelServiceImpl.showAvailableRooms()) {
            System.out.println(room);
        }
        System.out.println("\n");


        //show room of type
        System.out.println("Room of type King Room : " + hotelServiceImpl.showRoomByType(RoomType.KING_ROOM));
        System.out.println("Room of type Not Existing : " + hotelServiceImpl.showRoomByType(null));
        System.out.println("\n");


//        //show room reservedByUser
//        System.out.println("Rooms reserved by quest " + hotelServiceImpl.showGuests().get(0).getName() + ": " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
//        System.out.println("Rooms reserved by quest " + hotelServiceImpl.showGuests().get(2).getName() + ": " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(2)));


        //reserveRoomByType success
        try {
            hotelServiceImpl.reserveRoomByNumber(hotelServiceImpl.showGuests().get(0), "1A");
            System.out.println("\nreservation success");


        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        //  System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));


        //reserveRoomByType fail
        try {

            hotelServiceImpl.reserveRoomByNumber(hotelServiceImpl.showGuests().get(2), "2A");

        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }

        try {

            hotelServiceImpl.reserveRoomByNumber(hotelServiceImpl.showGuests().get(2), "15A");

        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        //cancel reservation
        //  System.out.println(hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));

        try {
            hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "3A");
            System.out.println("cancelled");
        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        // System.out.println(hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        try {
            hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "3A");
            System.out.println("cancelled");
        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }

    }
}
