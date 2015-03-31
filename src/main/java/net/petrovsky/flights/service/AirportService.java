package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Airport;
import java.util.List;

public interface AirportService {

    Airport save (Airport airport);

    boolean delete (String IATAcode);

    Airport getByName (String name);

    Airport getByCountry (String country);

    List<Airport> getAll();
}
