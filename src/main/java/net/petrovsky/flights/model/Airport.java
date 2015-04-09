package net.petrovsky.flights.model;

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
}
