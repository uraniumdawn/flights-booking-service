package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.AirportService;
import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.service.OrderService;
import net.petrovsky.flights.service.UserService;
import net.petrovsky.flights.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    /*Common section*/
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String main() {
        return "admin";
    }


    /*User section*/
    @RequestMapping(value = "/admin/users/management", method = RequestMethod.GET)
    public String userManagement () {
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/by/secondname", method = RequestMethod.GET)
    public String selectUserBySecondName (@RequestParam(value = "second_name") String secondName, Model model) {
        List<User> users = userService.getBySecondName(secondName);
        if (!users.isEmpty()) {
            model.addAttribute("selectedUsers", users);
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/users/management";
    }

    @RequestMapping(value = "/admin/users/select/by/email", method = RequestMethod.GET)
    public String selectUserByEmail (@RequestParam(value = "email") String email, Model model) {
        User user = userService.getByEmail(email);
        if (user != null) {
            model.addAttribute("selectedUsers", Collections.singletonList(user));
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/users/management";
    }

    @RequestMapping(value = "/admin/users/select/all", method = RequestMethod.GET)
    public String selectAllUsers (Model model) {
        model.addAttribute("selectedUsers", userService.getAll());
        return "forward:/admin/users/management";
    }

    @RequestMapping(value = "/admin/users/state", method = RequestMethod.GET)
    public String stateUser (@RequestParam(value = "user_id") String userId, Model model) {
        User user = userService.getByID(Integer.valueOf(userId));
        user.setEnabled(!user.isEnabled());
        userService.update(user);
        return "forward:/admin/users/select/all";
    }

    /*Airport section*/
    @RequestMapping(value = "/admin/airports/management", method = RequestMethod.GET)
    public String airportManagement () {
        return "airportManagement";
    }

    @RequestMapping(value = "/admin/airports/select/by/iatacode", method = RequestMethod.GET)
    public String selectAirportsByIATAcode (@RequestParam(value = "IATA_code") String IATAcode, Model model) {
        Airport airport = airportService.getByIATAcode(IATAcode);
        if (airport != null) {
            model.addAttribute("selectedAirports", Collections.singletonList(airport));
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/airports/management";
    }

    @RequestMapping(value = "/admin/airports/select/by/name", method = RequestMethod.GET)
    public String selectAirportsByName (@RequestParam(value = "name") String name, Model model) {
        Airport airport = airportService.getByName(name);
        if (airport != null) {
            model.addAttribute("selectedAirports", Collections.singletonList(airport));
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/airports/management";
    }

    @RequestMapping(value = "/admin/airports/select/by/country", method = RequestMethod.GET)
    public String selectAirportsByCountry (@RequestParam(value = "country") String country, Model model) {
        List<Airport> airports = airportService.getByCountry(country);
        if (!airports.isEmpty()) {
            model.addAttribute("selectedAirports", airports);
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/airports/management";
    }

    @RequestMapping(value = "/admin/airports/select/all", method = RequestMethod.GET)
    public String selectAllAirports (Model model) {
        model.addAttribute("selectedAirports", airportService.getAll());
        return "forward:/admin/airports/management";
    }

    @RequestMapping(value = "/admin/airports/add", method = RequestMethod.GET)
    public String addAirport(){
        return "additionAirport";
    }

    @RequestMapping(value = "/admin/airports/add", method = RequestMethod.POST)
    public String addAirport(@RequestParam("IATA_code") String IATAcode,
                      @RequestParam("name") String name,
                      @RequestParam("city") String city,
                      @RequestParam("country") String country, Model model) {
        if (IATAcode.length() > 3){
            model.addAttribute("airport", new Airport(IATAcode, name, city, country));
            model.addAttribute("msgIATALength", "Length of IATA code must be less then 3 characters");
            return "additionAirport";
        }
        airportService.save(new Airport(IATAcode, name, city, country));
        return "redirect:/admin/airports/management";
    }

    @RequestMapping(value = "/admin/airports/edit", method = RequestMethod.GET)
    public String editAirport(@RequestParam("IATAcode") String IATAcode, Model model){
        model.addAttribute("airport", airportService.getByIATAcode(IATAcode));
        return "editingAirport";
    }

    @RequestMapping(value = "/admin/airports/edit", method = RequestMethod.POST)
    public String editAirport(@RequestParam("IATA_code") String IATAcode,
                              @RequestParam("name") String name,
                              @RequestParam("city") String city,
                              @RequestParam("country") String country, Model model) {
        if (IATAcode.length() > 3){
            model.addAttribute("msgIATALength", "Length of IATA code must be less then 3 characters");
            return "additionAirport";
        }
        airportService.update(new Airport(IATAcode, name, city, country));
        return "redirect:/admin/airports/management";
    }

    /*Flight section*/
    @RequestMapping(value = "/admin/flights/management", method = RequestMethod.GET)
    public String flightManagement (Model model) {
        model.addAttribute("airportList", airportService.getAll());
        return "flightManagement";
    }

    @RequestMapping(value = "/admin/flights/select/by/pointod", method = RequestMethod.GET)
    public String selectFlightsByPointOfDeparture (@RequestParam(value = "point_of_departure") String pointOfDeparture, Model model) {
        List<Flight> flights = flightService.getByPointOfDeparture(pointOfDeparture);
        if (!flights.isEmpty()) {
            model.addAttribute("selectedFlights_adm", flights);
            model.addAttribute("choicePOD", pointOfDeparture);
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/flights/management";
    }

    @RequestMapping(value = "/admin/flights/select/by/destination", method = RequestMethod.GET)
    public String selectFlightsByDestination (@RequestParam(value = "destination") String destination, Model model) {
        List<Flight> flights = flightService.getByDestination(destination);
        if (!flights.isEmpty()) {
            model.addAttribute("selectedFlights_adm", flights);
            model.addAttribute("choiceD", destination);
        } else {
            model.addAttribute("msgEmptyResult", "There are not items according your request");
        }
        return "forward:/admin/flights/management";
    }

    @RequestMapping(value = "/admin/flights/select/all", method = RequestMethod.GET)
    public String selectAllFlights (Model model) {
        model.addAttribute("selectedFlights_adm", flightService.getAll());
        return "forward:/admin/flights/management";
    }

    @RequestMapping(value = "/admin/flights/add", method = RequestMethod.GET)
    public String addFlight(Model model){
        model.addAttribute("airportList", airportService.getAll());
        return "additionFlight";
    }

    @RequestMapping(value = "/admin/flights/add", method = RequestMethod.POST)
    public String addFlight(@RequestParam("destination") String destination,
                            @RequestParam("point_of_departure") String pointOfDeparture,
                            @RequestParam("time") String time,
                            @RequestParam("price") String price) {
        flightService.save(new Flight(null, airportService.getByIATAcode(pointOfDeparture), airportService.getByIATAcode(destination),
                TimeUtil.toLocalDateTime(time), Double.valueOf(price)));
        return "redirect:/admin/flights/management";
    }

    @RequestMapping(value = "/admin/flights/edit", method = RequestMethod.GET)
    public String editFlight(@RequestParam("flight_id") String flightId, HttpSession session, Model model) {
        model.addAttribute("airportList", airportService.getAll());
        session.setAttribute("currentFlight", flightService.getByID(Integer.valueOf(flightId)));
        return "editingFlight";
    }

    @RequestMapping(value = "/admin/flights/edit", method = RequestMethod.POST)
    public String editFlight(@RequestParam("destination") String destination,
                             @RequestParam("point_of_departure") String pointOfDeparture,
                             @RequestParam("time") String time,
                             @RequestParam("price") String price, HttpSession session) {
        flightService.update(new Flight(((Flight) session.getAttribute("currentFlight")).getId(), airportService.getByIATAcode(pointOfDeparture),
                airportService.getByIATAcode(destination), TimeUtil.toLocalDateTime(time), Double.valueOf(price)));
        session.removeAttribute("currentFlight");
        return "redirect:/admin/flights/management";
    }

    /*Booking section*/
    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String userOrder (@RequestParam("user_id") String userId, Model model) {
        model.addAttribute("orders", orderService.getByUser(Integer.valueOf(userId)));
        return "adminOrders";
    }

}
