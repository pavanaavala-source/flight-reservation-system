import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightService {

    private final List<Flight> flights = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    public FlightService() {
        
        flights.add(new Flight("AN101", "San Francisco", LocalDateTime.of(2025, 12, 20, 10, 30), 50));
        flights.add(new Flight("AN102", "San Francisco", LocalDateTime.of(2025, 12, 20, 15, 45), 20));
        flights.add(new Flight("AN201", "New York", LocalDateTime.of(2025, 12, 21, 9, 0), 10));
    }

    
    public List<Flight> searchFlights(String destination, LocalDateTime dateTime) {
        List<Flight> result = new ArrayList<>();
        LocalDate targetDate = dateTime.toLocalDate();

        for (Flight flight : flights) {
            if (flight.getDestination().equalsIgnoreCase(destination)
                    && flight.getDepartureTime().toLocalDate().equals(targetDate)
                    && flight.getAvailableSeats() > 0) {
                result.add(flight);
            }
        }

        return result;
    }

   
    public Reservation bookFlight(String customerName, Flight flight, int seats) {
        if (seats <= 0) {
            return null;
        }

        if (flight.getAvailableSeats() - seats < 0) {
            
            return null;
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        Reservation reservation = new Reservation(customerName, flight, seats);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservationsForCustomer(String customerName) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation r : reservations) {
            if (r.getCustomerName().equalsIgnoreCase(customerName)) {
                result.add(r);
            }
        }
        return result;
    }

  
    public List<Flight> getAllFlights() {
        return flights;
    }
}

