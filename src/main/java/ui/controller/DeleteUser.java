package ui.controller;

import domain.model.DomainException;
import domain.model.Material;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteUser extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        User user = (User) session.getAttribute("user");

        if (user.getRole() != Role.MODERATOR ){
            request.setAttribute("message", "You must be logged in as a moderator to access this page");
            return "Controller?command=Home";
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            service.removeUser(id);
        } catch (DomainException e) {
            request.setAttribute("materialString", e.getMessage());
        } catch (NumberFormatException e) {
            request.setAttribute("materialString", "Invalid id");
        }

        return "Controller?command=Home";
    }
}
