package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.repository.RoleRepository;
import net.petrovsky.flights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private RoleRepository roleRepository;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public void init (DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users").usingColumns("first_name", "second_name",
                        "email", "password").usingGeneratedKeyColumns("id");
    }

    public User mapRow (ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRegistration(rs.getTimestamp("registration").toLocalDateTime());
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    }

    @Override
    @Transactional
    public User save (User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("first_name", user.getFirstName())
                .addValue("second_name", user.getSecondName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(mapSqlParameterSource);
            user.setId(newKey.intValue());
            roleRepository.create(user.getId(), Role.ROLE_USER);
            user.setRoles(Collections.singleton(Role.ROLE_USER));
        } else {
            //todo: Add realization of service message of existing such user
        }
        return user;
    }

    @Override
    @Transactional
    public User update (User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("first_name", user.getFirstName())
                .addValue("second_name", user.getSecondName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());
        namedParameterJdbcTemplate.update(
                "UPDATE users SET " +
                        "first_name=:first_name, " +
                        "second_name=:second_name, " +
                        "email=:email, " +
                        "password=:password, " +
                        "enabled=:enabled " +
                        "WHERE id=:id",
                mapSqlParameterSource);
        return user;
    }

    @Override
    public boolean delete (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.update(
                "DELETE FROM users WHERE id=:id", mapSqlParameterSource) != 0;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByID (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        User user = namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id=:id", mapSqlParameterSource, this::mapRow);
        user.setRoles(roleRepository.getByUserID(id));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getBySecondName (String secondName) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("second_name", secondName);
        List<User> users = namedParameterJdbcTemplate.query(
                "SELECT * FROM users WHERE second_name=:second_name",
                mapSqlParameterSource, this::mapRow);
        users.forEach((user) -> user.setRoles(roleRepository.getByUserID(user.getId())));
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail (String email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("email", email);
        User user = namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE email=:email",
                mapSqlParameterSource, this::mapRow);
        user.setRoles(roleRepository.getByUserID(user.getId()));
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll () {
        List<User> users = namedParameterJdbcTemplate.query(
                "SELECT * FROM users", this::mapRow);
        users.forEach((user) -> user.setRoles(roleRepository.getByUserID(user.getId())));
        return users;
    }
}
