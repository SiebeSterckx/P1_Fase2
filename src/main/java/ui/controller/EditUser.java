package ui.controller;

import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class EditUser extends RequestHandler {

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

        try{
            String id = request.getParameter("id");
            User user1 = service.findUserWithId(Integer.parseInt(id));
            request.setAttribute("emailPreviousValue", user1.getEmail());
            request.setAttribute("namePreviousValue", user1.getCompanyName());
            request.setAttribute("rolePreviousValue", user1.getRole().getStringValue());
            System.out.println(user1.getRole().getStringValue());
            request.setAttribute("id", id);
            return "editUser.jsp";
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid id");
        }

        return "Controller?command=Home";
    }
}
