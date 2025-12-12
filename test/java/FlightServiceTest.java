import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceTest {

    @Test
    public void testSearchFlightsFound() {
        FlightService service = new FlightService();
        LocalDateTime date = LocalDateTime.of(2025, 12, 20, 0, 0);

        List<Flight> flights = service.searchFlights("San Francisco", date);

        assertFalse(flights.isEmpty());
        for (Flight flight : flights) {
            assertEquals("San Francisco", flight.getDestination());
        }
    }

    @Test
    public void testSearchFlightsNotFound() {
        FlightService service = new FlightService();
        LocalDateTime date = LocalDateTime.of(2025, 1, 1, 0, 0);

        List<Flight> flights = service.searchFlights("Somewhere", date);

        assertTrue(flights.isEmpty());
    }

    @Test
    public void testBookFlightSuccess() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(0);
        int originalSeats = flight.getAvailableSeats();

        Reservation reservation = service.bookFlight("Pavan", flight, 2);

        assertNotNull(reservation);
        assertEquals(originalSeats - 2, flight.getAvailableSeats());
        assertEquals(2, reservation.getSeatsBooked());
    }

    @Test
    public void testBookFlightNotEnoughSeats() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(0);

        // Try to book more seats than available
        Reservation reservation = service.bookFlight("Pavan", flight, flight.getAvailableSeats() + 1);

        assertNull(reservation);
    }

    @Test
    public void testBookFlightInvalidSeats() {
        FlightService service = new FlightService();
        Flight flight = service.getAllFlights().get(0);

        Reservation reservation = service.bookFlight("Pavan", flight, 0);

        assertNull(reservation);
    }
}

