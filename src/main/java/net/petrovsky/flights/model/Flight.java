package net.petrovsky.flights.model;

import java.time.LocalDateTime;

public class Flight extends BaseEntity {

    private String pointOfDeparture;
    private String Destination;
    private LocalDateTime time;
    private Double price;

    public Flight () {
    }

    public Flight (Integer id, String pointOfDeparture, String destination, LocalDateTime time, Double price) {
        super(id);
        this.pointOfDeparture = pointOfDeparture;
        Destination = destination;
        this.time = time;
        this.price = price;
    }

    public String getPointOfDeparture () {
        return pointOfDeparture;
    }

    public void setPointOfDeparture (String pointOfDeparture) {
        this.pointOfDeparture = pointOfDeparture;
    }

    public String getDestination () {
        return Destination;
    }

    public void setDestination (String destination) {
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
}
