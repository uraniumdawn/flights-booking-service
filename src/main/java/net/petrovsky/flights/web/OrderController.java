package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Flight;
import net.petrovsky.flights.model.Order;
import net.petrovsky.flights.model.User;
import net.petrovsky.flights.service.FlightService;
import net.petrovsky.flights.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class OrderController {

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
        if(session.getAttribute("orders") == null) {
            indexSet = new HashSet<>();
            session.setAttribute("orders", indexSet);
        } else {
            indexSet = (Set)session.getAttribute("orders");
        }

        Integer userID = ((User) session.getAttribute("user")).getId();
        if(!orderService.getByUserAndFlight(userID, Integer.valueOf(flightId)).isEmpty()) {
            indexSet.add(Integer.valueOf(flightId));
            session.setAttribute("orders", indexSet);
        }
        return "forward:/";
    }

    @RequestMapping(value = "/preorder/delete", method = RequestMethod.GET)
    public String deleteFromPreorder(@RequestParam("flight_id") String flightId, HttpSession session) {
        ((Map)session.getAttribute("preorder")).remove(flightId);
        return "forward:/";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String makeAnOrder(@RequestParam("flight_id") String flightId, Model model, HttpSession session) {
        Set<Integer> indexList = (Set)session.getAttribute("orders");
        if(!indexList.contains(Integer.valueOf(flightId))) {
            orderService.save(new Order(null, ((User) session.getAttribute("user")), flightService.getByID(Integer.valueOf(flightId))));
            indexList.add(Integer.valueOf(flightId));
            session.setAttribute("orders", indexList);
        } else {
            model.addAttribute("msgExistentOrder", "You have ordered already this flight");
        }
        return "forward:/";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listOfUserOrders(Model model, HttpSession session) {
        model.addAttribute("orders", orderService.getByUser(((User) session.getAttribute("user")).getId()));
        return "orders";
    }
}
