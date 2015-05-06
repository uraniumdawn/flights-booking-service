package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Booking;

import java.util.List;

public interface BookingRepository {

    Booking save (Booking booking);

    Booking update (Booking booking);

    boolean delete (int id);

    Booking getByID (int id);

    List<Booking> getByUser (int userID);

    List<Booking> getByFlight (int flightID);
}
