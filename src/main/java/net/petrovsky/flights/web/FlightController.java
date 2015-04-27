package net.petrovsky.flights.web;

import net.petrovsky.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("flightList", flightService.getAll());
        return "flightList";
    }
}
