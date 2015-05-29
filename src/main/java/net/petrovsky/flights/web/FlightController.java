package net.petrovsky.flights.web;

import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/flights/select", method = RequestMethod.GET)
    public String selectFlights(@RequestParam("destination") String destination,
                                @RequestParam("point_of_departure") String pointOfDeparture,
                                @RequestParam("from") String from,
                                @RequestParam("to") String to,
                                HttpSession session, Model model) {
        if (TimeUtil.toDate(from).isAfter(LocalDateTime.now().toLocalDate().minusDays(1)) && TimeUtil.toDate(to).isAfter(LocalDateTime.now().toLocalDate().minusDays(1))) {
            session.setAttribute("selectedFlights", flightService.getFlightToOrder(destination, pointOfDeparture,
                    TimeUtil.toDate(from).atTime(0, 0, 0), TimeUtil.toDate(to).atTime(0, 0, 0)));
        } else {
            model.addAttribute("msgIncorrectDates", "Incorrect dates");
        }

        Map choice = new HashMap<>();
        choice.put("destination", destination);
        choice.put("point_of_departure", pointOfDeparture);
        choice.put("from", from);
        choice.put("to", to);
        session.setAttribute("choice", choice);
        return "forward:/";
    }
}
