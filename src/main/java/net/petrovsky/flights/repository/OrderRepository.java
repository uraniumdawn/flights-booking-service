package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save (Order order);

    Order update (Order order);

    boolean delete (int id);

    Order getByID (int id);

    List<Order> getByUser (int userID);

    List<Order> getByFlight (int flightID);
}
