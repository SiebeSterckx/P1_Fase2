package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OverviewVerdeler extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }

        request.setAttribute("success", session.getAttribute("success"));
        session.removeAttribute("success");
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("materials", service.getMaterialsForVerdeler(user));

        if (user.getRole() == Role.VERDELER) {
            return "overviewverdeler.jsp";
        } else {
            return "Controller?command=Home";
        }
    }

}
