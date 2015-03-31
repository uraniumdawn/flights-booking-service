package net.petrovsky.flights.model;

public class Airport extends BaseEntity {

    private String IATAcode;
    private String name;
    private String city;
    private String country;

    public Airport () {
    }

    public Airport (Integer id, String IATAcode, String name, String city) {
        super(id);
        this.IATAcode = IATAcode;
        this.name = name;
        this.city = city;
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
