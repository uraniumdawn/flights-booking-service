package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.User;

import java.util.List;

public interface UserRepositoty {

    User save (User user);

    boolean delete (int id);

    User getByID (int id);

    User getByEmail (String email);

    User getBySecondName (String secondName);

    List<User> getAll ();
}
