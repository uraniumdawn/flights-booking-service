package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Booking;
import net.petrovsky.flights.repository.BookingRepository;
import net.petrovsky.flights.repository.FlightRepository;
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
public class BookingRepositoryJdbcImpl implements BookingRepository{

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
                .withTableName("booking").usingGeneratedKeyColumns("id");
    }

    public Booking mapRow (ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setUser(userRepository.getByID(rs.getInt("user_id")));
        booking.setFlight(flightRepository.getByID(rs.getInt("flight_id")));
        return booking;
    }

    @Override
    public Booking save (Booking booking, int userID, int flightID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", booking.getId())
                .addValue("user_id", userID)
                .addValue("flight_id", flightID);
        if (booking.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(mapSqlParameterSource);
            booking.setId(newKey.intValue());
        } else {
            //todo: Add realization of service message of existing such flight
        }
        return booking;
    }

    @Override
    public Booking update (Booking booking, int userID, int flightID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", booking.getId())
                .addValue("user_id", userID)
                .addValue("flight_id", flightID);
        namedParameterJdbcTemplate.update("UPDATE booking SET user_id=:user_id, flight_id=:flight_id WHERE id=:id",
                mapSqlParameterSource);
        return booking;
    }

    @Override
    public boolean delete (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.update("UPDATE booking SET user_id=:user_id, flight_id=:flight_id WHERE id=:id",
                mapSqlParameterSource) != 0;
    }

    @Override
    public Booking getByID (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM booking WHERE id=:id",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Booking> getByUser (int userID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userID);
        return namedParameterJdbcTemplate.query("SELECT * FROM booking WHERE user_id=:user_id", mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Booking> getByFlight (int flightID) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("flight_id", flightID);
        return namedParameterJdbcTemplate.query("SELECT * FROM booking WHERE flight_id=:flight_id", mapSqlParameterSource, this::mapRow);
    }
}
