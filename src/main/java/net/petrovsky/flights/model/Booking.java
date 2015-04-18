package net.petrovsky.flights.model;

import java.util.Objects;

public class Booking extends BaseEntity {

    private User user;
    private Flight flight;

    public Booking () {
    }

    public Booking (Integer id, User user, Flight flight) {
        super(id);
        this.user = user;
        this.flight = flight;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    public Flight getFlight () {
        return flight;
    }

    public void setFlight (Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString () {
        return "Booking{" +
                "id=" + super.getId() +
                ", user=" + user +
                ", flight=" + flight +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(user, booking.user) &&
                Objects.equals(flight, booking.flight);
    }

    @Override
    public int hashCode () {
        return Objects.hash(user, flight);
    }
}
