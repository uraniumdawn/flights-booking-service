package net.petrovsky.flights.web;

import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String editprofile() {
        return "editprofile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public void editprofile(@RequestParam("first_name") String firstName,
                         @RequestParam("second_name") String secondName,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password, HttpSession session) {
        User  user = (User)session.getAttribute("user");
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setPassword(password);
        session.setAttribute("user", userService.update(user));
    }
}
