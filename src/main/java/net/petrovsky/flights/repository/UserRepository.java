package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.User;
import java.util.List;

public interface UserRepository {

    User save (User user);

    User update (User user);

    boolean delete (int id);

    User getByID (int id);

    User getByEmail (String email);

    List<User> getBySecondName (String secondName);

    List<User> getAll ();
}
