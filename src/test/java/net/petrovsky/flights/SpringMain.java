package net.petrovsky.flights;

import net.petrovsky.flights.repository.jdbc.UserRepositoryJdbcImpl;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {

        try (GenericXmlApplicationContext ctx = new GenericXmlApplicationContext()) {
            ctx.load("spring/spring-app.xml");
            ctx.refresh();
            System.out.println("\n======\n" + Arrays.toString(ctx.getBeanDefinitionNames()) + "\n======\n");
            UserRepositoryJdbcImpl userRepositoryJdbc = ctx.getBean(UserRepositoryJdbcImpl.class);
            userRepositoryJdbc.delete(0);
        }
    }
}
