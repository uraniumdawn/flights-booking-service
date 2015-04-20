package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role update(int user_id, Role role);
}
