package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.repository.AirportRepository;
import net.petrovsky.flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FlightsRepositoryJdbcImpl implements FlightRepository{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    public void init (DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("flights").usingGeneratedKeyColumns("id");
    }

    public Flight mapRow (ResultSet rs, int rowNum) throws SQLException {
        Flight flight = new Flight();
        flight.setId(rs.getInt("id"));
        flight.setPointOfDeparture(airportRepository.getByIATAcode(rs.getString("point_of_departure")));
        flight.setDestination(airportRepository.getByIATAcode(rs.getString("destination")));
        flight.setTime(rs.getTimestamp("time").toLocalDateTime());
        flight.setPrice(rs.getDouble("price"));
        return flight;
    }

    @Override
    public Flight save (Flight flight) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", flight.getId())
                .addValue("point_of_departure", flight.getPointOfDeparture().getIATAcode())
                .addValue("destination", flight.getDestination().getIATAcode())
                .addValue("time", Timestamp.valueOf(flight.getTime()))
                .addValue("price", flight.getPrice());
        if (flight.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(mapSqlParameterSource);
            flight.setId(newKey.intValue());
        } else {
            //todo: Add realization of service message of existing such flight
        }
        return flight;
    }

    @Override
    public Flight update (Flight flight) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", flight.getId())
                .addValue("point_of_departure", flight.getPointOfDeparture().getIATAcode())
                .addValue("destination", flight.getDestination().getIATAcode())
                .addValue("time", Timestamp.valueOf(flight.getTime()))
                .addValue("price", flight.getPrice());
        namedParameterJdbcTemplate.update(
                "UPDATE flights SET point_of_departure=:point_of_departure, destination=:destination, time=:time, price=:price WHERE id=:id",
                mapSqlParameterSource);
        return flight;
    }

    @Override
    public boolean delete (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.update("DELETE FROM flights WHERE id=:id", mapSqlParameterSource) != 0;
    }

    @Override
    public Flight getByID (int id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM flights WHERE id=:id", mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Flight> getByPointOfDeparture (String pointOfDeparture) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("point_of_departure", pointOfDeparture);
        return namedParameterJdbcTemplate.query("SELECT * FROM flights WHERE point_of_departure=:point_of_departure",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Flight> getByDestination (String destination) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("destination", destination);
        return namedParameterJdbcTemplate.query("SELECT * FROM flights WHERE destination=:destination",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Flight> getBetween (LocalDateTime from, LocalDateTime to) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("from", Timestamp.valueOf(from))
                .addValue("to", Timestamp.valueOf(to));
        return namedParameterJdbcTemplate.query("SELECT * FROM flights WHERE time >=:from AND time <:to ORDER BY time DESC",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Flight> getFlightToOrder (String pointOfDeparture, String destination, LocalDateTime from, LocalDateTime to) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("point_of_departure", pointOfDeparture)
                .addValue("destination", destination)
                .addValue("from", Timestamp.valueOf(from))
                .addValue("to", Timestamp.valueOf(to));
        return namedParameterJdbcTemplate.query("SELECT * FROM flights WHERE time >=:from AND time <:to AND point_of_departure=:point_of_departure AND destination=:destination" +
                        " ORDER BY time DESC",
                mapSqlParameterSource, this::mapRow);
    }


    @Override
    public List<Flight> getAll () {
        return namedParameterJdbcTemplate.query("SELECT * FROM flights", this::mapRow);
    }
}
