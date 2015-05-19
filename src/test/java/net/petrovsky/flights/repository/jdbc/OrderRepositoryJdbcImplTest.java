package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Order;
import net.petrovsky.flights.repository.FlightRepository;
import net.petrovsky.flights.repository.OrderRepository;
import net.petrovsky.flights.repository.UserRepository;
import net.petrovsky.flights.util.DbPopulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryJdbcImplTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void doPopulate() throws Exception {
        dbPopulator.execute();
    }
//
//    @Test
//    public void testSave () throws Exception {
//        Booking booking = bookingRepository.save(new Booking(null, userRepository.getByID(1001), flightRepository.getByID(1008)), 1001, 1008);
//        Assert.assertEquals(bookingRepository.getByID(1018), booking);
//    }
//
//    @Test
//    public void testUpdate () throws Exception {
//        Booking booking = bookingRepository.update(new Booking(1010, userRepository.getByID(1001), flightRepository.getByID(1004)), 1001, 1004);
//        Assert.assertEquals(bookingRepository.getByID(1010), booking);
//    }
//
//    @Test (expected = InvalidDataAccessApiUsageException.class)
//    public void testDelete () throws Exception {
//        bookingRepository.save(new Booking(null, userRepository.getByID(1001), flightRepository.getByID(1008)), 1001, 1008);
//        bookingRepository.delete(1018);
//    }

    @Test
    public void testGetByID () throws Exception {
        Order order = new Order(null, userRepository.getByID(1000), flightRepository.getByID(1004));
        Assert.assertEquals(orderRepository.getByID(1010), order);
    }

    @Test
    public void testGetByUser () throws Exception {
        Order[] three= {
                new Order(null, userRepository.getByID(1000), flightRepository.getByID(1004)),
                new Order(null, userRepository.getByID(1000), flightRepository.getByID(1007)),
                new Order(null, userRepository.getByID(1000), flightRepository.getByID(1005))};
        Assert.assertArrayEquals(three, orderRepository.getByUser(1000).toArray());
    }

    @Test
    public void testGetByFlight () throws Exception {
//        Order[] two= {
//                new Order(null, userRepository.getByID(1000), flightRepository.getByID(1004)),
//                new Order(null, userRepository.getByID(1003), flightRepository.getByID(1004))};
//        Assert.assertArrayEquals(two, orderRepository.getByFlight(1004).toArray());
        System.out.println(orderRepository.getByUserAndFlight(1000, 1004).isEmpty());

//        orderRepository.save(new Order(null, userRepository.getByID(1000), flightRepository.getByID(1004)));

    }
}