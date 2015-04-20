package net.petrovsky.flights.service;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role update (int user_id, Role role) {
        return roleRepository.update(user_id, role);
    }
}
