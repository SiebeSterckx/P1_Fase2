package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModOverviewMatPage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        User user = (User) request.getSession().getAttribute("user");
        
        if (!(user.getRole().equals(Role.MODERATOR))) {
            request.setAttribute("message", "Not authorized to view this page");
            return "index.jsp";
        }
        request.setAttribute("materials", service.getMaterials());
        return "moderatorMaterialsOverview.jsp";
    }

}