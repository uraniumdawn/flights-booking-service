package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Airport;

import java.util.List;

public interface AirportRepository {

    Airport save (Airport airport);

    Airport update (Airport airport);

    boolean delete (String IATAcode);

    Airport getByIATAcode (String IATAcode);

    Airport getByName (String name);

    List<Airport> getByCountry (String country);

    List<Airport> getAll();
}
