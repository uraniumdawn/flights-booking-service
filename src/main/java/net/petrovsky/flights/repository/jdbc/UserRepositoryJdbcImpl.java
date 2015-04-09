package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.User;
import net.petrovsky.flights.model.UserBuilder;
import net.petrovsky.flights.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public void init (DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users").usingColumns("first_name", "second_name",
                        "email", "password").usingGeneratedKeyColumns("id");
    }

    public User mapRow (ResultSet rs, int rowNum) throws SQLException {
        UserBuilder userBuilder = UserBuilder.anUser()
                .withId(rs.getInt("id"))
                .withFirstName(rs.getString("first_name"))
                .withSecondName(rs.getString("second_name"))
                .withEmail(rs.getString("email"))
                .withPassword(rs.getString("password"))
                .withRegistration(rs.getTimestamp("registration").toLocalDateTime())
                .withEnabled(rs.getBoolean("enabled"));
        userBuilder.build();

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
    public User save (User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("first_name", user.getFirstName())
                .addValue("second_name", user.getSecondName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("enabled", user.isEnabled());
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(mapSqlParameterSource);
            user.setId(newKey.intValue());
        } else {
            //todo: Add realization of service message of existing such user
        }
        return user;
    }

    @Override
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
                "DELETE FROM users " +
                        "WHERE id=:id",
                mapSqlParameterSource) != 0;
    }

    @Override
    public User getByID (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT id, first_name, second_name, email, password, registration, enabled " +
                        "FROM users " +
                        "WHERE id=:id",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<User> getBySecondName (String secondName) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("second_name", secondName);
        return namedParameterJdbcTemplate.query(
                "SELECT id, first_name, second_name, email, password, registration, enabled " +
                        "FROM users " +
                        "WHERE second_name=:second_name",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public User getByEmail (String email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("email", email);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT id, first_name, second_name, email, password, registration, enabled " +
                        "FROM users " +
                        "WHERE email=:email",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<User> getAll () {
        return namedParameterJdbcTemplate.query(
                "SELECT id, first_name, second_name, email, password, registration, enabled " +
                        "FROM users",
                this::mapRow);
    }
}
