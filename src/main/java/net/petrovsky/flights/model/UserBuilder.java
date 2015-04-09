package net.petrovsky.flights.model;

import java.time.LocalDateTime;
import java.util.Set;

public class UserBuilder {
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private LocalDateTime registration;
    private boolean enabled;
    private Set<Role> roles;
    private Integer id;

    private UserBuilder () {
    }

    public static UserBuilder anUser () {
        return new UserBuilder();
    }

    public UserBuilder withFirstName (String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withSecondName (String secondName) {
        this.secondName = secondName;
        return this;
    }

    public UserBuilder withEmail (String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword (String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withRegistration (LocalDateTime registration) {
        this.registration = registration;
        return this;
    }

    public UserBuilder withEnabled (boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder withRoles (Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserBuilder withId (Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder but () {
        return anUser().withFirstName(firstName).withSecondName(secondName)
                .withEmail(email).withPassword(password).withRegistration(registration)
                .withEnabled(enabled).withRoles(roles).withId(id);
    }

    public User build () {
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRegistration(registration);
        user.setEnabled(enabled);
        user.setRoles(roles);
        user.setId(id);
        return user;
    }
}
