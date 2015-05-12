package net.petrovsky.flights.web;

import net.petrovsky.flights.model.Role;
import net.petrovsky.flights.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminActionInterceptor implements HandlerInterceptor {
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

    @Override
    public void postHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //NOP
    }

    @Override
    public void afterCompletion (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //NOP
    }
}
