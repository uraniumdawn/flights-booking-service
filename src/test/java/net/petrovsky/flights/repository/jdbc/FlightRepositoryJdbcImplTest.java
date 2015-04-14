package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.repository.FlightRepository;
import net.petrovsky.flights.util.DbPopulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

@ContextConfiguration("classpath:spring/spring-app-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FlightRepositoryJdbcImplTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private FlightRepository flightRepository;

    Flight lax;
    Flight lar;

    @Before
    public void doPopulate() throws Exception {
        dbPopulator.execute();
        lax = new Flight(1004, "YYZ", "YVR", LocalDateTime.of(2014, 10, 20, 17, 05, 00), 125.00);
        lar = new Flight(null, "YUC", "YYZ", LocalDateTime.of(2014, 1, 8, 6, 10, 00), 96.00); ;
    }

    private static final Flight[] all = {
            new Flight(1004, "YYZ", "YVR", LocalDateTime.of(2014, 10, 20, 17, 05, 00), 125.00),
            new Flight(1005, "YVR", "YYZ", LocalDateTime.of(2014, 11, 14, 04, 17, 00), 152.00),
            new Flight(1006, "YYZ", "YUC", LocalDateTime.of(2014, 9, 07, 10, 45, 00), 95.00),
            new Flight(1007, "ATL", "LAX", LocalDateTime.of(2014, 7, 18, 17, 58, 00), 58.00),
            new Flight(1008, "YYZ", "ATL", LocalDateTime.of(2014, 6, 07, 15, 24, 00), 115.00),
            new Flight(1009, "YUC", "ORD", LocalDateTime.of(2014, 5, 01, 03, 07, 00), 45.00)};

    @Test
    public void testSave () throws Exception {
        flightRepository.save(lar);
        Assert.assertEquals(lar, flightRepository.getByID(1018));
    }

    @Test (expected = DataIntegrityViolationException.class)
    public void testDuplicatePointAndDest () throws Exception {
        flightRepository.save(new Flight(null, "ORD", "ORD", LocalDateTime.of(2015, 4, 30, 6, 45), 500.00));
    }

    @Test
    public void testUpdate () throws Exception {
        lax.setPrice(1000.00);
        flightRepository.update(lax);
        Assert.assertEquals(lax, flightRepository.getByID(1004));
    }

    @Test
    public void testDelete () throws Exception {
        flightRepository.save(lar);
        flightRepository.delete(1018);
        Assert.assertArrayEquals(all, flightRepository.getAll().toArray());

    }

    @Test
    public void testGetByID () throws Exception {
        Assert.assertEquals(all[0], flightRepository.getByID(1004));
        }

    @Test
    public void testGetByPointOfDeparture () throws Exception {
        Flight[] actRes = {all[0], all[2], all[4]};
        Assert.assertArrayEquals(actRes, flightRepository.getByPointOfDeparture("YYZ").toArray());

    }

    @Test
    public void testGetByDestination () throws Exception {
        Flight[] actRes = {all[0]};
        Assert.assertArrayEquals(actRes, flightRepository.getByDestination("YVR").toArray());
    }

    @Test
    public void testGetByBetween () throws Exception {
        LocalDateTime from = LocalDateTime.of(2014, 5, 02, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2014, 10, 20, 17, 05, 00);
        Flight[] actRes = {all[2], all[3], all[4]};
        Assert.assertArrayEquals(actRes, flightRepository.getBetween(from, to).toArray());
    }

    @Test
    public void testGetAll () throws Exception {
        Assert.assertArrayEquals(all, flightRepository.getAll().toArray());
    }
}