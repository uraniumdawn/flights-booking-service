package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Order;
import net.petrovsky.flights.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save (Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update (Order order) {
        return orderRepository.update(order);
    }

    @Override
    public boolean delete (int id) {
        return orderRepository.delete(id);
    }

    @Override
    public Order getByID (int id) {
        return orderRepository.getByID(id);
    }

    @Override
    public List<Order> getByUser (int userID) {
        return orderRepository.getByUser(userID);
    }

    @Override
    public List<Order> getByFlight (int flightID) {
        return orderRepository.getByFlight(flightID);
    }

    @Override
    public List<Order> getByUserAndFlight (int userID, int flightID) {
        return orderRepository.getByUserAndFlight(userID, flightID);
    }

    @Override
    public List<Order> getAll () {
        return orderRepository.getAll();
    }
}
