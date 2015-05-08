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

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String main(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        if (user != null) {
            if(user.getRole().equals(Role.ROLE_ADMIN)) {
                return "admin";
            } else {
                model.addAttribute("accessDenied", "Access denied");
                return "admin";
            }
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/admin/users/management", method = RequestMethod.GET)
    public String userManagement () {
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/bysecondname", method = RequestMethod.GET)
    public String selectUserBySecondName (@RequestParam(value = "second_name") String secondName, Model model) {
        if (secondName != null) {
            model.addAttribute("selectedUsers", userService.getBySecondName(secondName));
        } else {
            model.addAttribute("error", "You did not add any value");
        }
        return "userManagement";
    }

    @RequestMapping(value = "/admin/users/select/byemail", method = RequestMethod.GET)
    public String selectUserByEmail (@RequestParam(value = "email") String email, Model model) {
        if (email != null) {
            model.addAttribute("selectedUsers", Collections.singletonList(userService.getByEmail(email)));
        } else {
            model.addAttribute("error", "You did not add any value");
        }
        return "userManagement";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String deleteUser (@RequestParam(value = "user_id") String user_id, Model model) {
        userService.delete(Integer.valueOf(user_id));
        model.addAttribute("deletingSuccessful", "Removal user with"  + user_id + "was successful");
        return "forward:/admin/users/management";
    }

}
