package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Airport;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.AirportService;
import net.petrovsky.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AirportService airportService;

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

    @RequestMapping(value = "/admin/users/select/bysecondname", method = RequestMethod.GET)
    public String selectUserBySecondName (@RequestParam(value = "second_name") String secondName, Model model) {
        model.addAttribute("selectedUsers", userService.getBySecondName(secondName));
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/byemail", method = RequestMethod.GET)
    public String selectUserByEmail (@RequestParam(value = "email") String email, Model model) {
        User user = userService.getByEmail(email);
        if (user != null) {
            model.addAttribute("selectedUsers", Collections.singletonList(user));
        } else {
            model.addAttribute("emptyResult", "There are not items according your request");
        }
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/all", method = RequestMethod.GET)
    public String selectAllUsers (Model model) {
        model.addAttribute("selectedUsers", userService.getAll());
        return "userManagement";
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

    @RequestMapping(value = "/admin/airports/select/byiatacode", method = RequestMethod.GET)
    public String selectAirportsByIATAcode (@RequestParam(value = "IATA_code") String IATAcode, Model model) {
        Airport airport = airportService.getByIATAcode(IATAcode);
        if (airport != null) {
            model.addAttribute("selectedAirports", Collections.singletonList(airport));
        } else {
            model.addAttribute("emptyResult", "There are not items according your request");
        }
        return "airportManagement";
    }

    @RequestMapping(value = "/admin/airports/select/byname", method = RequestMethod.GET)
    public String selectAirportsByName (@RequestParam(value = "name") String name, Model model) {
        Airport airport = airportService.getByName(name);
        if (airport != null) {
            model.addAttribute("selectedAirports", Collections.singletonList(airport));
        } else {
            model.addAttribute("emptyResult", "There are not items according your request");
        }
        return "airportManagement";
    }

    @RequestMapping(value = "/admin/airports/select/bycountry", method = RequestMethod.GET)
    public String selectAirportsByCountry (@RequestParam(value = "country") String country, Model model) {
        model.addAttribute("selectedAirports", airportService.getByCountry(country));
        return "airportManagement";
    }

    @RequestMapping(value = "/admin/airports/select/all", method = RequestMethod.GET)
    public String selectAllAirports (Model model) {
        model.addAttribute("selectedAirports", airportService.getAll());
        return "airportManagement";
    }

    @RequestMapping(value = "/admin/airports/addnew", method = RequestMethod.GET)
    public String add(){
        return "additionAirport";
    }

    @RequestMapping(value = "/admin/airports/addnew", method = RequestMethod.POST)
    public String add(@RequestParam("IATA_code") String IATAcode,
                      @RequestParam("name") String name,
                      @RequestParam("city") String city,
                      @RequestParam("country") String country, Model model) {
        if (IATAcode.length() > 3){
            model.addAttribute("enteredValueIATA", IATAcode);
            model.addAttribute("enteredValueName", name);
            model.addAttribute("enteredValueCity", city);
            model.addAttribute("enteredValueCountry", country);
            model.addAttribute("msgIATALength", "Length of IATA code must be less then 3 characters");
            return "additionAirport";
        }
        airportService.save(new Airport(IATAcode, name, city, country));
        return "redirect:/admin/airports/management";
    }
}
