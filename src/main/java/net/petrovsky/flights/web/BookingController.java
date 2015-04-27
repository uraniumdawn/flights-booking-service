package net.petrovsky.flights.web;

import net.petrovsky.flights.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/airports", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("airportList", bookingService.getByID(1010));
        return "airportList";
    }
}
