package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.User;
import net.petrovsky.flights.repository.UserRepository;
import net.petrovsky.flights.util.DbPopulator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ContextConfiguration({
//        "classpath:spring/spring-app-test.xml",
//        "classpath:spring/spring-db.xml"
//})
@ContextConfiguration("classpath:spring/spring-app-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryJdbcImplTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void doPopulate() throws Exception {
        dbPopulator.execute();
    }

    @Autowired
    private UserRepository userRepository;

    private static final User[] all = {
            new User(1000, "Laura", "Palmer", "WhoKillLauraPalmer@linch.com", "FireWalkWithMe", null, true, null),
            new User(1001, "Dale", "Cooper", "coop@linch.com", "cherrypie", null, true, null),
            new User(1002, "Maddy", "Ferguson", "Sheryl@linch.com", "Lee", null, true, null),
            new User(1003, "Audrey", "Horne", "Sherilyn@linch.com", "Fenn", null, true, null)};

    @Test
    public void testSave () throws Exception {
        User donna = new User(null, "Donna", "Hayward", "Lara_Flynn_Boyle@linch.com", "James", null, true, null);
        userRepository.save(donna);
        Assert.assertEquals(userRepository.getByID(1018), donna);
    }

    @Test
    public void testUpdate () throws Exception {
        User shelly = new User(null, "Shelly", "Johnson", "Leo@linch.com", "Bobby", null, true, null);
        userRepository.save(shelly);
        shelly.setEmail("Die_Leo_Die@linch.com");
        userRepository.update(shelly);
        Assert.assertEquals(userRepository.getByID(1018), shelly);
    }

    @Test(expected = DuplicateKeyException.class)
    public void testDuplicateMailSave() throws Exception {
        User shelly = new User(null, "Shelly", "Johnson", "Leo@linch.com", "Bobby", null, true, null);
        User bot = new User(null, "bot", "bot", "Leo@linch.com", "bot", null, true, null);
        userRepository.save(shelly);
        userRepository.save(bot);
    }

    @Test
    public void testDelete () throws Exception {
        User donna = new User(null, "Donna", "Hayward", "Lara_Flynn_Boyle@linch.com", "James", null, true, null);
        userRepository.save(donna);
        userRepository.delete(1018);
        Assert.assertArrayEquals(all, userRepository.getAll().toArray());
    }

    @Test
    public void testGetByID () throws Exception {
        Assert.assertEquals(all[0], userRepository.getByID(1000));
    }

    @Test
    public void testGetByEmail () throws Exception {
        User shelly = new User(null, "Shelly", "Johnson", "Leo@linch.com", "Bobby", null, true, null);
        userRepository.save(shelly);
        Assert.assertEquals(shelly, userRepository.getByEmail("Leo@linch.com"));
    }

    @Test
    public void testGetBySecondName () throws Exception {
        User shelly = new User(null, "Shelly", "Johnson", "Leo@linch.com", "Bobby", null, true, null);
        User leo = new User(null, "Leo", "Johnson", "Renault@linch.com", "Hank", null, true, null);
        userRepository.save(shelly);
        userRepository.save(leo);
        Object[] johnsons = {shelly, leo};
        Assert.assertArrayEquals(johnsons, userRepository.getBySecondName("Johnson").toArray());
    }

    @Test
    public void testGetAll () throws Exception {
        Assert.assertArrayEquals(all, userRepository.getAll().toArray());
    }
}