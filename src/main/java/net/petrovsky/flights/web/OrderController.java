package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.model.Order;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes({"preorder", "ordersIndex", "user"})
public class  OrderController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/preorder/add", method = RequestMethod.GET)
    public String addToPreorder(@RequestParam("flight_id") String flightId, HttpSession session) {
        if (session.getAttribute("preorder") == null) {
            Map<String, Flight> preorder = new HashMap<>();
            preorder.put(flightId, flightService.getByID(Integer.valueOf(flightId)));
            session.setAttribute("preorder", preorder);
        } else {
            ((Map)session.getAttribute("preorder")).put(flightId, flightService.getByID(Integer.valueOf(flightId)));
        }

        Set<Integer> indexSet;
        if(session.getAttribute("ordersIndex") == null) {
            indexSet = new HashSet<>();
            session.setAttribute("ordersIndex", indexSet);
        } else {
            indexSet = (Set)session.getAttribute("ordersIndex");
        }

        Integer userID = ((User) session.getAttribute("user")).getId();
        if(!orderService.getByUserAndFlight(userID, Integer.valueOf(flightId)).isEmpty()) {
            indexSet.add(Integer.valueOf(flightId));
            session.setAttribute("ordersIndex", indexSet);
        }
        return "forward:/";
    }

    @RequestMapping(value = "/preorder/delete", method = RequestMethod.GET)
    public String deleteFromPreorder(@RequestParam("flight_id") String flightId, @ModelAttribute("preorder") Map preorder) {
        preorder.remove(flightId);
        return "forward:/";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String makeAnOrder(@RequestParam("flight_id") String flightId, @ModelAttribute("ordersIndex") Set indexSet, @ModelAttribute("user") User user, Model model) {
        if(!indexSet.contains(Integer.valueOf(flightId))) {
            orderService.save(new Order(null, user, flightService.getByID(Integer.valueOf(flightId))));
            indexSet.add(Integer.valueOf(flightId));
            model.addAttribute("ordersIndex", indexSet);
        } else {
            //NOP
        }
        return "forward:/";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listOfUserOrders(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("orders", orderService.getByUser(user.getId()));
        return "orders";
    }
}
