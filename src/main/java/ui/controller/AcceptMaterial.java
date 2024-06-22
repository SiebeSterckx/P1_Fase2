package ui.controller;


import domain.model.Notification;

import domain.model.Role;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class AcceptMaterial extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        if (user.getRole() != Role.VERDELER) {
            request.setAttribute("message", "You must be a verdeler to accept/reject materials");
            return "overviewverdeler.jsp";
        }
        String id = request.getParameter("id");
        service.acceptMaterial(id);
        service.addNotification(new Notification(service.getMaterial(Integer.parseInt(id)).getProviderId(), LocalDateTime.now(), "Your material has been accepted"));
        request.setAttribute("materials", service.getMaterialsForVerdeler(user));
        return "overviewverdeler.jsp";
    }
}
