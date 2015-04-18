package net.petrovsky.flights.web;

import net.petrovsky.flights.repository.UserRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private WebApplicationContext webApplicationContext;

    @Override
    public void init (ServletConfig config) throws ServletException {
        super.init(config);
        webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository userRepository = webApplicationContext.getBean(UserRepository.class);
        request.setAttribute("userList", userRepository.getAll());
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    }
}
