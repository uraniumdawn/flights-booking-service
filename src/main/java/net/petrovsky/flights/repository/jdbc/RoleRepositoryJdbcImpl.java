package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleRepositoryJdbcImpl implements RoleRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Role mapRow (ResultSet rs, int rowNum) throws SQLException {
        return Role.valueOf(rs.getString("role"));
    }

    @Override
    public Role create (int user_id, Role role) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", user_id)
                .addValue("role", role.toString());
        namedParameterJdbcTemplate.update("INSERT INTO user_roles (user_id, role) VALUES (:user_id, :role)", mapSqlParameterSource);
        return role;
    }

    @Override
    public Role update (int user_id, Role role) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", user_id)
                .addValue("role", role.toString());
        namedParameterJdbcTemplate.update("UPDATE user_roles SET role=:role WHERE user_id=:user_id", mapSqlParameterSource);
        return role;
    }

    @Override
    public boolean delete (int user_id, Role role) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", user_id)
                .addValue("role", role.toString());
        return namedParameterJdbcTemplate.update("DELETE FROM user_roles WHERE user_id=:user_id", mapSqlParameterSource) != 0;
        //todo: realize constraint for user that have al least one row
    }

    @Override
    public List<Role> getAllRoles () {
        return namedParameterJdbcTemplate.query("SELECT * FROM user_roles", this::mapRow);
    }

    @Override
    public List<Role> getByUserID (int user_id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", user_id);
        return namedParameterJdbcTemplate.query("SELECT * FROM user_roles WHERE user_id=:user_id", mapSqlParameterSource, this::mapRow);
    }
}
