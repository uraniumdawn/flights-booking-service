package net.petrovsky.flights.model;

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
}
