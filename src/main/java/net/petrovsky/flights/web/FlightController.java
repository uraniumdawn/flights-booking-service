package net.petrovsky.flights.web;

import net.petrovsky.flights.service.AirportService;
import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirportService airportService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("flightList", flightService.getAll());
        return "flightList";
    }

    @RequestMapping(value = "/selectflights", method = RequestMethod.GET)
    public String selectFlights(@RequestParam("destination") String destination,
                                @RequestParam("point_of_departure") String point_of_departure,
                                @RequestParam("from") String from,
                                @RequestParam("to") String to,
                                HttpSession session) {
        session.setAttribute("selectedFlightList", flightService.getFlightToOrder(destination, point_of_departure,
                TimeUtil.toDate(from).atTime(0, 0, 0), TimeUtil.toDate(to).atTime(0, 0, 0)));
        return "redirect:/";
    }
}
