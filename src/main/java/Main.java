import com.jaspersoft.model.Guest;
import com.jaspersoft.model.Room;
import com.jaspersoft.repository.HotelRepositoryImpl;
import com.jaspersoft.service.HotelService;
import com.jaspersoft.service.HotelServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(HotelServiceImpl.class, HotelRepositoryImpl.class);
        HotelService service = context.getBean("hotelServiceImpl", HotelService.class);
        //  System.out.println(context.getBeansOfType(Object.class ));


        //hotel instance
        System.out.println(service.findAll() + " \n");

        // show all rooms
        System.out.println("All rooms: ");
        for (Room room : service.showAllRooms()) {
            System.out.println(room);
        }
        System.out.println("\n");


        //show all guests
        System.out.println("All guests: ");
        for (Guest guest : service.showGuests()) {
            System.out.println(guest);
        }
        System.out.println("\n");

        //show available rooms
        System.out.println("Available rooms: ");
        for (Room room : service.showAvailableRooms()) {
            System.out.println(room);
        }
        System.out.println("\n");


        //show room of type
        System.out.println("Room of type King Room : " + service.showRoomByType("King Room"));
        System.out.println("Room of type Not Existing : " + service.showRoomByType("Not Existing"));
        System.out.println("\n");


        //show room reservedByUser
        System.out.println("Rooms reserved by quest: " + service.showRoomsReservedByUser(service.showGuests().get(0)));
        System.out.println("Rooms reserved by quest: " + service.showRoomsReservedByUser(service.showGuests().get(2)));


        //reserveRoom success
        if (service.reserveRoomForSpecificUser(service.showGuests().get(0), "1A")) {
            System.out.println("\nreservation success");
        }

        System.out.println("Rooms reserved by quest: " + service.showRoomsReservedByUser(service.showGuests().get(0)));

        //reserveRoom fail
        if (!service.reserveRoomForSpecificUser(service.showGuests().get(2), "2A")) {
            System.out.println("\nfailed to reserve");
        }


        //cancel reservation
        System.out.println("\nCancel reservation");
        service.cancelReservation(service.showGuests().get(0), "1A");
        System.out.println("Rooms reserved by quest: " + service.showRoomsReservedByUser(service.showGuests().get(0)));
        service.cancelReservation(service.showGuests().get(0), "3A");
        System.out.println("Rooms reserved by quest: " + service.showRoomsReservedByUser(service.showGuests().get(0)));
        System.out.println(service.showGuests().get(0));


    }
}
