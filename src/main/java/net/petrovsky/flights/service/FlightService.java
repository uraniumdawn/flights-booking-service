package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Flight;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    Flight save (Flight flight);

    boolean delete (int id);

    Flight getByID (int id);

    Flight getByPointOfDeparture (String pointOfDeparture);

    Flight getByDestination (String destination);

    List<Flight> getBetween (LocalDateTime from, LocalDateTime to);

    List<Flight> getAll();
}
