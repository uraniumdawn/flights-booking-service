package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.repository.AirportRepository;
import net.petrovsky.flights.util.DbPopulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-app-test.xml",
        "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AirportRepositoryJdbcImplTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private AirportRepository airportRepository;

    Airport atlanta;
    Airport dallas;

    @Before
    public void doPopulate() throws Exception {
        dbPopulator.execute();
        dallas = new Airport("DFW", "Dallas-Fort Worth", "Dallas", "USA");
        atlanta = new Airport("ATL", "Hartsfield-Jackson Atlanta International Airport", "Atlanta", "USA");
    }

    private static final Airport[] all = {
            new Airport("ATL", "Hartsfield-Jackson Atlanta International Airport", "Atlanta", "USA"),
            new Airport("LAX", "Los Angeles International Airport", "Los Angeles", "USA"),
            new Airport("ORD", "Chicago OHare International Airport", "Chiсago", "USA"),
            new Airport("YYZ", "Toronto Pearson International Airport", "Toronto", "Canada"),
            new Airport("YVR", "Vancouver International Airport", "Vancouver", "Canada"),
            new Airport("YUC", "Calgary International Airport", "Calgary", "Canada")
    };

    @Test
    public void testSave () throws Exception {
        airportRepository.save(dallas);
        Assert.assertEquals(airportRepository.getByIATAcode("DFW"), dallas);
    }

    @Test
    public void testUpdate () throws Exception {
        atlanta.setName("Atlanta Airport");
        airportRepository.update(atlanta);
        Assert.assertEquals(airportRepository.getByIATAcode("ATL"), atlanta);
    }

    @Test(expected = DuplicateKeyException.class)
    public void testDuplicateNameSave() throws Exception {
        Airport bot = new Airport("bot", "Hartsfield-Jackson Atlanta International Airport", "bot", "bot");
        airportRepository.save(bot);
    }

    @Test
    public void testDelete () throws Exception {
        airportRepository.save(dallas);
        airportRepository.delete("DFW");
        Assert.assertArrayEquals(all, airportRepository.getAll().toArray());
    }

    @Test
    public void testGetByIATAcode () throws Exception {
        Assert.assertEquals(all[0], airportRepository.getByIATAcode("ATL"));
    }

    @Test
    public void testGetByName() throws Exception {
        Assert.assertEquals(atlanta, airportRepository.getByName("Hartsfield-Jackson Atlanta International Airport"));
    }

    @Test
    public void testGetBySecondName () throws Exception {
        Object[] usas = {all[0], all[1], all[2]};
        Assert.assertArrayEquals(usas, airportRepository.getByCountry("USA").toArray());
    }

    @Test
    public void testGetAll () throws Exception {
        Assert.assertArrayEquals(all, airportRepository.getAll().toArray());
    }
}