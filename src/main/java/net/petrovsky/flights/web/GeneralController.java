package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.AirportService;
import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.service.OrderService;
import net.petrovsky.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class GeneralController {

    @Autowired
    private UserService userService;

    @Autowired
    private AirportService airportService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("airportList", airportService.getAll());
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, /*HttpSession session,*/ Model model) {
        if (userService.check(email, password)){
            User user = userService.getByEmail(email);
            model.addAttribute("user", user);
            return user.getRole().equals(Role.ROLE_ADMIN) ? "redirect:/admin" : "redirect:/";
        } else {
            model.addAttribute("msgIncorrectCredentials", "Invalid email/password or you have not registered yet!");
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "forward:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@RequestParam("first_name") String firstName,
                         @RequestParam("second_name") String secondName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password, HttpSession session) {
        session.setAttribute("user", userService.save(new User(null, firstName, secondName, email, password, null, true, Role.ROLE_USER)));
        return "main";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("airports", airportService.getAll());
        model.addAttribute("flights", flightService.getAll());
        model.addAttribute("orders", orderService.getAll());
        return "all";
    }
}
