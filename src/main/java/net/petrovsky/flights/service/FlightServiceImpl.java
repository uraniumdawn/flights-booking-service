package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight save (Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight update (Flight flight) {
        return flightRepository.update(flight);
    }

    @Override
    public boolean delete (int id) {
        return flightRepository.delete(id);
    }

    @Override
    public Flight getByID (int id) {
        return flightRepository.getByID(id);
    }

    @Override
    public List<Flight> getByPointOfDeparture (String pointOfDeparture) {
        return flightRepository.getByPointOfDeparture(pointOfDeparture);
    }

    @Override
    public List<Flight> getByDestination (String destination) {
        return flightRepository.getByDestination(destination);
    }

    @Override
    public List<Flight> getBetween (LocalDateTime from, LocalDateTime to) {
        return flightRepository.getBetween(from, to);
    }

    @Override
    public List<Flight> getAll () {
        return flightRepository.getAll();
    }
}
