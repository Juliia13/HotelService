package com.jaspersoft.hotelServiceProject;

import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.service.HotelService;
import com.jaspersoft.hotelServiceProject.service.HotelServiceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HotelService hotelServiceImpl = context.getBean("hotelServiceImpl", HotelService.class);


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
        System.out.println("Room of type King Room : " + hotelServiceImpl.showRoomByType("King Room"));
        System.out.println("Room of type Not Existing : " + hotelServiceImpl.showRoomByType("Not Existing"));
        System.out.println("\n");


        //show room reservedByUser
        System.out.println("Rooms reserved by quest " + hotelServiceImpl.showGuests().get(0).getName() + ": " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        System.out.println("Rooms reserved by quest " + hotelServiceImpl.showGuests().get(2).getName() + ": " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(2)));


        //reserveRoom success
        try {
            hotelServiceImpl.reserveRoomForSpecificUser(hotelServiceImpl.showGuests().get(0), "1A");
            System.out.println("\nreservation success");


        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));


        //reserveRoom fail
        try {

            hotelServiceImpl.reserveRoomForSpecificUser(hotelServiceImpl.showGuests().get(2), "2A");

        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }

        try {

            hotelServiceImpl.reserveRoomForSpecificUser(hotelServiceImpl.showGuests().get(2), "15A");

        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        //cancel reservation
        System.out.println(hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));

        try {
            hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "3A");
            System.out.println("cancelled");
        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }


        System.out.println(hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        try {
            hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "3A");
            System.out.println("cancelled");
        } catch (HotelServiceException exc) {
            System.out.println(exc.getMessage());
        }

    }
}
