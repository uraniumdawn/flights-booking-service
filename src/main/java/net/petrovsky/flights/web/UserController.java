package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password, Model model) {
        return userService.check(email, password) ? "main" : "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User signup(@RequestParam("first_name") String firstName,
                         @RequestParam("second_name") String secondName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password, Model model) {
        return userService.save(new User(null, firstName, secondName, email, password, null, true, Role.ROLE_USER));
    }
}
