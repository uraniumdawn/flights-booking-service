package net.petrovsky.flights.model;

import java.time.LocalDateTime;
import java.util.*;

public class User extends BaseEntity {

    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private LocalDateTime registration;
    private boolean enabled;
    private Set<Role> roles;

    public User() {
    }

    public User (Integer id, String firstName, String secondName, String email, String password,
                 LocalDateTime registered, boolean enabled, Set<Role> roles) {
        super(id);
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.registration = registered;
        this.enabled = enabled;
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

    public LocalDateTime getRegistration () {
        return registration;
    }

    public void setRegistration (LocalDateTime registration) {
        this.registration = registration;
    }

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = Collections.unmodifiableSet(EnumSet.copyOf(roles));
    }

    @Override
    public String toString () {
        return "User{" +
                "id=" + super.getId() +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registration=" + registration +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode () {
        return Objects.hash(firstName, secondName, email, password, enabled);
    }


}
