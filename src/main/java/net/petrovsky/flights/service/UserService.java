package net.petrovsky.flights.service;

import net.petrovsky.flights.model.User;
import java.util.List;

public interface UserService {

    User save (User user);

    boolean delete (int id);

    User getByID (int id);

    User getByEmail (String email);

    User getBySecondName (String secondName);

    List<User> getAll ();
}
