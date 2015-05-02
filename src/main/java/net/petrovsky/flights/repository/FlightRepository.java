package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Flight;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository {

    Flight save (Flight flight);

    Flight update (Flight flight);

    boolean delete (int id);

    Flight getByID (int id);

    List<Flight> getByPointOfDeparture (String pointOfDeparture);

    List<Flight> getByDestination (String destination);

    List<Flight> getBetween (LocalDateTime from, LocalDateTime to);

    List<Flight> getFlightToOrder (String pointOfDeparture, String destination, LocalDateTime from, LocalDateTime to);

    List<Flight> getAll();
}
