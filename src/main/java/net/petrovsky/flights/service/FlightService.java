package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface FlightService {

    Flight save (Flight flight);

    Flight update (Flight flight);

    boolean delete (int id);

    Flight getByID (int id);

    List<Flight> getByPointOfDeparture (String pointOfDeparture);

    List<Flight> getByDestination (String destination);

    List<Flight> getBetween (LocalDateTime from, LocalDateTime to);

    List<Flight> getAll();
}
