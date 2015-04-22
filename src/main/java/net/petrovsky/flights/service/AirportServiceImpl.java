package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public Airport save (Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update (Airport airport) {
        return airportRepository.update(airport);
    }

    @Override
    public boolean delete (String IATAcode) {
        return airportRepository.delete(IATAcode);
    }

    @Override
    public Airport getByName (String name) {
        return airportRepository.getByName(name);
    }

    @Override
    public List<Airport> getByCountry (String country) {
        return airportRepository.getByCountry(country);
    }

    @Override
    public List<Airport> getAll () {
        return airportRepository.getAll();
    }
}
