package net.petrovsky.flights.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Flight extends BaseEntity {

    private Airport pointOfDeparture;
    private Airport Destination;
    private LocalDateTime time;
    private Double price;

    public Flight () {
    }

    public Flight (Integer id, Airport pointOfDeparture, Airport destination, LocalDateTime time, Double price) {
        super(id);
        this.pointOfDeparture = pointOfDeparture;
        Destination = destination;
        this.time = time;
        this.price = price;
    }

    public Airport getPointOfDeparture () {
        return pointOfDeparture;
    }

    public void setPointOfDeparture (Airport pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public Airport getDestination () {
        return Destination;
    }

    public void setDestination (Airport destination) {
        Destination = destination;
    }

    public LocalDateTime getTime () {
        return time;
    }

    public void setTime (LocalDateTime time) {
        this.time = time;
    }

    public Double getPrice () {
        return price;
    }

    public void setPrice (Double price) {
        this.price = price;
    }

    @Override
    public String toString () {
        return "Flight{" +
                "id=" + super.getId() +
                ", pointOfDeparture='" + pointOfDeparture + '\'' +
                ", Destination='" + Destination + '\'' +
                ", time=" + time +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(pointOfDeparture, flight.pointOfDeparture) &&
                Objects.equals(Destination, flight.Destination) &&
                Objects.equals(time, flight.time) &&
                Objects.equals(price, flight.price);
    }

    @Override
    public int hashCode () {
        return Objects.hash(pointOfDeparture, Destination, time, price);
    }
}
