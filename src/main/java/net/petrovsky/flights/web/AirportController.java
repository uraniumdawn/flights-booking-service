package net.petrovsky.flights.web;

import net.petrovsky.flights.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AirportController {

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "/airports", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("airportList", airportService.getAll());
        return "airportList";
    }
}
