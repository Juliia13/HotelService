import com.jaspersoft.hotelServiceProject.AppConfig;
import com.jaspersoft.hotelServiceProject.model.Guest;
import com.jaspersoft.hotelServiceProject.model.Room;
import com.jaspersoft.hotelServiceProject.service.HotelService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HotelService hotelServiceImpl = context.getBean("hotelServiceImpl", HotelService.class);
//        System.out.println(context.getBeansOfType(Object.class ));

        //hotel instance
        System.out.println(hotelServiceImpl.findAll() + " \n");

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
        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(2)));


        //reserveRoom success
        if (hotelServiceImpl.reserveRoomForSpecificUser(hotelServiceImpl.showGuests().get(0), "1A")) {
            System.out.println("\nreservation success");
        }

        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));

        //reserveRoom fail
        if (!hotelServiceImpl.reserveRoomForSpecificUser(hotelServiceImpl.showGuests().get(2), "2A")) {
            System.out.println("\nfailed to reserve");
        }


        //cancel reservation
        System.out.println("\nCancel reservation");
        hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "1A");
        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        hotelServiceImpl.cancelReservation(hotelServiceImpl.showGuests().get(0), "3A");
        System.out.println("Rooms reserved by quest: " + hotelServiceImpl.showRoomsReservedByUser(hotelServiceImpl.showGuests().get(0)));
        System.out.println(hotelServiceImpl.showGuests().get(0));


    }
}
