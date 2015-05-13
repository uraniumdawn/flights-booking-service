package net.petrovsky.flights.web.interceptors;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminActionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            if(user.getRole().equals(Role.ROLE_ADMIN)) {
                return true;
            } else {
                httpServletRequest.setAttribute("accessDenied", "You don't have corresponding authorization rule");
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/login");
                requestDispatcher.forward(httpServletRequest, httpServletResponse);
                return false;
            }
        } else {
            httpServletResponse.sendRedirect("/login");
            return false;
        }
    }
}
