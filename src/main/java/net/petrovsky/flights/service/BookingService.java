package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Booking;
import java.util.List;

public interface BookingService {

    Booking save (Booking booking, int userID, int flightID);

    Booking update (Booking booking, int userID, int flightID);

    boolean delete (int id);

    Booking getByID (int id);

    List<Booking> getByUser (int userID);

    List<Booking> getByFlight (int flightID);
}
