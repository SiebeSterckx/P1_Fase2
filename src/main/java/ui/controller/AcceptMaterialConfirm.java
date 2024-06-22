package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AcceptMaterialConfirm extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        if ((user.getRole() != Role.VERDELER)) {
            request.setAttribute("message", "You must be a verdeler to accept/reject materials");
            request.setAttribute("materials", service.getMaterialsForVerdeler(user));
            return "overviewverdeler.jsp";
        }
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);
            request.setAttribute("variable", "acceptMaterial");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid id");
            return "materialsoverview.jsp";
        }
        return "confirmAction.jsp";
    }
}