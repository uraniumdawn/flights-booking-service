package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Role;

import java.util.List;

public interface RoleRepository {

    Role create(int user_id, Role role);

    Role update(int user_id, Role role);

    boolean delete(int user_id, Role role);

    List<Role> getAllRoles();

    List<Role> getByUserID(int user_id);
}
