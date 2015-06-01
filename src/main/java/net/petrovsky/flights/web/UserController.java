package net.petrovsky.flights.web;

import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String editProfile() {
        return "editprofile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String editProfile(@RequestParam("first_name") String firstName,
                         @RequestParam("second_name") String secondName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password, Model model, @ModelAttribute("user") User user) {
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(password);
        model.addAttribute("user", userService.update(user));
        return "redirect:/";
    }
}
