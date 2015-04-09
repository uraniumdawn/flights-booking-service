package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.repository.AirportRepository;
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
public class AirportRepositoryJdbcImpl implements AirportRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertAirport;

    @Autowired
    public void init (DataSource dataSource) {
        this.insertAirport = new SimpleJdbcInsert(dataSource)
                .withTableName("airports").usingGeneratedKeyColumns("id");
    }

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

        if (airport.getIATAcode() == null) {
            namedParameterJdbcTemplate.update(
                    "INSERT INTO airports (iata_code, name, city, country) " +
                            "VALUES (:iata_code, :name, :city, :country)",
                    mapSqlParameterSource);
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE airports SET iata_code=:iata_code, name=:name, city=:city, country=:country",
                    mapSqlParameterSource);
        }
        return airport;
    }

    @Override
    public Airport update (Airport airport) {
        return airport;
    }

    @Override
    public boolean delete (String IATAcode) {
        return false;
    }

    @Override
    public Airport getByName (String name) {
        return null;
    }

    @Override
    public Airport getByCountry (String country) {
        return null;
    }

    @Override
    public List<Airport> getAll () {
        return null;
    }
}
