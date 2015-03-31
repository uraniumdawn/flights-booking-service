package net.petrovsky.flights.model;

import java.time.LocalDateTime;
import java.util.Set;

public class User extends BaseEntity {

    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private boolean enabled;
    private LocalDateTime registered;
    private Set<Role> roles;

    public User() {
    }

    public User (Integer id, String firstName, String secondName, String email, String password,
                 boolean enabled, LocalDateTime registered, Set<Role> roles) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = roles;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName () {
        return secondName;
    }

    public void setSecondName (String secondName) {
        this.secondName = secondName;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public boolean isEnabled () {
        return enabled;
    }

    public void setEnabled (boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered () {
        return registered;
    }

    public void setRegistered (LocalDateTime registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles (Set<Role> roles) {
        this.roles = roles;
    }
}
