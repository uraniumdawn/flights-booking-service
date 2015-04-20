package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Airport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {

    Airport save (Airport airport);

    Airport update (Airport airport);

    boolean delete (String IATAcode);

    Airport getByName (String name);

    List<Airport> getByCountry (String country);

    List<Airport> getAll();
}
