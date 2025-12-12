import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        FlightService flightService = new FlightService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Simple Flight Reservation System");

        boolean running = true;

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Search flights");
            System.out.println("2. Book a flight");
            System.out.println("3. View my reservations");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleSearchFlights(scanner, flightService);
                    break;
                case "2":
                    handleBookFlight(scanner, flightService);
                    break;
                case "3":
                    handleViewReservations(scanner, flightService);
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void handleSearchFlights(Scanner scanner, FlightService service) {
        try {
            System.out.print("Enter destination: ");
            String destination = scanner.nextLine();

            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString, DATE_FORMAT);

            List<Flight> flights = service.searchFlights(destination, date.atStartOfDay());

            if (flights.isEmpty()) {
                System.out.println("No flights found for that destination and date.");
            } else {
                System.out.println("Available flights:");
                for (int i = 0; i < flights.size(); i++) {
                    System.out.println((i + 1) + ". " + flights.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while searching flights. " +
                    "Please check your input and try again.");
        }
    }

    private static void handleBookFlight(Scanner scanner, FlightService service) {
        try {
            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter destination: ");
            String destination = scanner.nextLine();

            System.out.print("Enter date (yyyy-MM-dd): ");
            String dateString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dateString, DATE_FORMAT);

            List<Flight> flights = service.searchFlights(destination, date.atStartOfDay());

            if (flights.isEmpty()) {
                System.out.println("No flights available to book.");
                return;
            }

            System.out.println("Available flights:");
            for (int i = 0; i < flights.size(); i++) {
                System.out.println((i + 1) + ". " + flights.get(i));
            }

            System.out.print("Choose a flight number from the list: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index < 0 || index >= flights.size()) {
                System.out.println("Invalid flight selection.");
                return;
            }

            Flight selectedFlight = flights.get(index);

            System.out.print("How many seats do you want to book? ");
            int seats = Integer.parseInt(scanner.nextLine());

            Reservation reservation = service.bookFlight(customerName, selectedFlight, seats);

            if (reservation == null) {
                System.out.println("Booking failed. Maybe not enough seats " +
                        "are available.");
            } else {
                System.out.println("Booking successful: " + reservation);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong while booking the flight. " +
                    "Please check your input and try again.");
        }
    }

    private static void handleViewReservations(Scanner scanner, FlightService service) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        List<Reservation> reservations = service.getReservationsForCustomer(customerName);

        if (reservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            System.out.println("Your reservations:");
            for (Reservation r : reservations) {
                System.out.println(r);
            }
        }
    }
}

