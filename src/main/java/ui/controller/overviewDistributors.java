package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class overviewDistributors extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user.getRole() == Role.MODERATOR) {
            request.setAttribute("users", service.getAllDistributors());
            return "overviewDistributors.jsp";
        } else {
            request.setAttribute("message", "You must be logged in as a moderator to access this page");
            return "Controller?command=Home";
        }

    }

}
