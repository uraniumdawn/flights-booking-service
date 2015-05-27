package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Order;
import net.petrovsky.flights.repository.FlightRepository;
import net.petrovsky.flights.repository.OrderRepository;
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
public class OrderRepositoryJdbcImpl implements OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void init (DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("flight_order").usingGeneratedKeyColumns("id");
    }

    public Order mapRow (ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setUser(userRepository.getByID(rs.getInt("user_id")));
        order.setFlight(flightRepository.getByID(rs.getInt("flight_id")));
        return order;
    }

    @Override
    public Order save (Order order) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("user_id", order.getUser().getId())
                .addValue("flight_id", order.getFlight().getId());
        if (order.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(mapSqlParameterSource);
            order.setId(newKey.intValue());
        } else {
            //todo: Add realization of service message of existing such flight
        }
        return order;
    }

    @Override
    public Order update (Order order) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("user_id", order.getUser().getId())
                .addValue("flight_id", order.getFlight().getId());
        namedParameterJdbcTemplate.update("UPDATE flight_order SET user_id=:user_id, flight_id=:flight_id WHERE id=:id",
                mapSqlParameterSource);
        return order;
    }

    @Override
    public boolean delete (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.update("UPDATE flight_order SET user_id=:user_id, flight_id=:flight_id WHERE id=:id",
                mapSqlParameterSource) != 0;
    }

    @Override
    public Order getByID (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM flight_order WHERE id=:id",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Order> getByUser (int userID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userID);
        return namedParameterJdbcTemplate.query("SELECT * FROM flight_order WHERE user_id=:user_id ORDER BY flight_id DESC", mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Order> getByFlight (int flightID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("flight_id", flightID);
        return namedParameterJdbcTemplate.query("SELECT * FROM flight_order WHERE flight_id=:flight_id", mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Order> getByUserAndFlight (int userID, int flightID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userID)
                .addValue("flight_id", flightID);
        return namedParameterJdbcTemplate.query("SELECT * FROM flight_order WHERE user_id=:user_id AND flight_id=:flight_id", mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Order> getAll () {
        return namedParameterJdbcTemplate.query("SELECT * FROM flight_order ORDER BY id DESC", this::mapRow);
    }
}
