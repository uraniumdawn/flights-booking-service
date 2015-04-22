package net.petrovsky.flights.service;

import net.petrovsky.flights.model.User;
import net.petrovsky.flights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save (User user) {
        return userRepository.save(user);
    }

    @Override
    public User update (User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean delete (int id) {
        return userRepository.delete(id);
    }

    @Override
    public User getByID (int id) {
        return userRepository.getByID(id);
    }

    @Override
    public User getByEmail (String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User>getBySecondName (String secondName) {
        return userRepository.getBySecondName(secondName);
    }

    @Override
    public List<User> getAll () {
        return userRepository.getAll();
    }
}
