package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Order;

import java.util.List;

public interface OrderService {

    Order save (Order order);

    Order update (Order order);

    boolean delete (int id);

    Order getByID (int id);

    List<Order> getByUser (int userID);

    List<Order> getByFlight (int flightID);
}
