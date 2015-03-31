package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Airport;

import java.util.List;

public interface AirportRepository {

    Airport save (Airport airport);

    boolean delete (String IATAcode);

    Airport getByName (String name);

    Airport getByCountry (String country);

    List<Airport> getAll();
}
