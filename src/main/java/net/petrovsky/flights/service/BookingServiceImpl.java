package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Booking;
import net.petrovsky.flights.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking save (Booking booking, int userID, int flightID) {
        return bookingRepository.save(booking, userID, flightID);
    }

    @Override
    public Booking update (Booking booking, int userID, int flightID) {
        return bookingRepository.update(booking, userID, flightID);
    }

    @Override
    public boolean delete (int id) {
        return bookingRepository.delete(id);
    }

    @Override
    public Booking getByID (int id) {
        return bookingRepository.getByID(id);
    }

    @Override
    public List<Booking> getByUser (int userID) {
        return bookingRepository.getByUser(userID);
    }

    @Override
    public List<Booking> getByFlight (int flightID) {
        return bookingRepository.getByFlight(flightID);
    }
}
