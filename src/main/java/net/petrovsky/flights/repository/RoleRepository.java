package net.petrovsky.flights.repository;

import net.petrovsky.flights.model.Role;

public interface RoleRepository {

    Role create(int user_id, Role role);

    Role update(int user_id, Role role);

//    boolean delete(int user_id, Role role);

//    List<Role> getAllRoles();

    Role getByUserID(int user_id);
}
