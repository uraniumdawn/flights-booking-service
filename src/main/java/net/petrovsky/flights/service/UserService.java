package net.petrovsky.flights.service;

import net.petrovsky.flights.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User save (User user);

    User update(User user);

    boolean delete (int id);

    User getByID (int id);

    User getByEmail (String email);

    List<User> getBySecondName (String secondName);

    List<User> getAll ();
}
