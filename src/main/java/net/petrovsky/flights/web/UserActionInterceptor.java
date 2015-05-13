package net.petrovsky.flights.web;

import net.petrovsky.flights.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserActionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user != null) {
            if(user.isEnabled() == true) {
                return true;
            } else {
                httpServletResponse.sendRedirect("/");
                return false;
            }
        } else {
            httpServletResponse.sendRedirect("/");
            return false;
        }
    }
}
