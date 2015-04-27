package net.petrovsky.flights.repository.jdbc;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.repository.RoleRepository;
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

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleRepositoryJdbcImplTest {

    @Autowired
    private DbPopulator dbPopulator;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void doPopulate() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testCreate() throws Exception {
        userRepository.save (new User(null, "Shelly", "Johnson", "Leo@linch.com", "Bobby", null, true, Role.ROLE_USER));
        Assert.assertEquals(Role.ROLE_USER, roleRepository.getByUserID(1018));
    }

    @Test (expected = DuplicateKeyException.class)
    public void testCreateWhithConstraint () throws Exception {
        roleRepository.create(1000, Role.ROLE_ADMIN);
    }

    @Test
    public void testUpdate () throws Exception {
        Assert.assertEquals(Role.ROLE_ADMIN, roleRepository.update(1001, Role.ROLE_ADMIN));
    }

    @Test
    public void testGetByUserID () throws Exception {
        Assert.assertEquals(Role.ROLE_ADMIN, roleRepository.getByUserID(1000));
    }
}