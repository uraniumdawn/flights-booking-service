package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AirportRepositoryJdbcImpl implements AirportRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Airport mapRow (ResultSet rs, int rowNum) throws SQLException {
        Airport airport = new Airport(rs.getString("iata_code"),
                rs.getString("name"),
                rs.getString("city"),
                rs.getString("country"));
        return airport;
    }

    @Override
    public Airport save (Airport airport) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("iata_code", airport.getIATAcode())
                .addValue("name", airport.getName())
                .addValue("city", airport.getCity())
                .addValue("country", airport.getCountry());
        if (airport.getIATAcode() != null) {
            namedParameterJdbcTemplate.update(
                    "INSERT INTO airports (iata_code, name, city, country) " +
                            "VALUES (:iata_code, :name, :city, :country)",
                    mapSqlParameterSource);
        } else {
            //todo: Add realization of service message of existing such airport
        }
        return airport;
    }

    @Override
    public Airport update (Airport airport) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("iata_code", airport.getIATAcode())
                .addValue("name", airport.getName())
                .addValue("city", airport.getCity())
                .addValue("country", airport.getCountry());
        namedParameterJdbcTemplate.update(
                "UPDATE airports SET iata_code=:iata_code, name=:name, city=:city, country=:country WHERE iata_code=:iata_code",
                mapSqlParameterSource);
        return airport;
    }

    @Override
    public boolean delete (String IATAcode) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("iata_code", IATAcode);
        return namedParameterJdbcTemplate.update("DELETE FROM airports WHERE iata_code=:iata_code",
                mapSqlParameterSource) != 0;
    }

    @Override
    public Airport getByIATAcode (String IATAcode) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("iata_code", IATAcode);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM airports WHERE iata_code=:iata_code",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public Airport getByName (String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("name", name);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM airports WHERE name=:name",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Airport> getByCountry (String country) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("country", country);
        return namedParameterJdbcTemplate.query("SELECT * FROM airports WHERE country=:country",
                mapSqlParameterSource, this::mapRow);
    }

    @Override
    public List<Airport> getAll () {
        return namedParameterJdbcTemplate.query("SELECT * FROM airports", this::mapRow);
    }
}
