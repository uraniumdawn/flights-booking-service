package net.petrovsky.flights.web;

import net.petrovsky.flights.model.User;
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

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String main() {
        return "admin";
    }

    @RequestMapping(value = "/admin/users/management", method = RequestMethod.GET)
    public String userManagement () {
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/bysecondname", method = RequestMethod.GET)
    public String selectUserBySecondName (@RequestParam(value = "second_name") String secondName, Model model) {
        if (secondName != "") {
            model.addAttribute("selectedUsers", userService.getBySecondName(secondName));
        } else {
            model.addAttribute("errorName", "You did not add any value");
        }
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/byemail", method = RequestMethod.GET)
    public String selectUserByEmail (@RequestParam(value = "email") String email, Model model) {
        if (email != "") {
            User user = userService.getByEmail(email);
            if (user != null) {
                model.addAttribute("selectedUsers", Collections.singletonList(user));
            } else {
                model.addAttribute("emptyResult", "There are not items according your request");
            }
        } else {
            model.addAttribute("errorEmail", "You did not add any value");
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
}
