package net.petrovsky.flights.model;

import java.util.Objects;

public class Airport {

    private String IATAcode;
    private String name;
    private String city;
    private String country;

    public Airport () {
    }

    public Airport (String IATAcode, String name, String city, String country) {
        this.IATAcode = IATAcode;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public String getIATAcode () {
        return IATAcode;
    }

    public void setIATAcode (String IATAcode) {
        this.IATAcode = IATAcode;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }

    @Override
    public String toString () {
        return "Airport{" +
                "IATAcode='" + IATAcode + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(IATAcode, airport.IATAcode) &&
                Objects.equals(name, airport.name);
    }

    @Override
    public int hashCode () {
        return Objects.hash(IATAcode, name, city, country);
    }
}
