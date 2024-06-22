package ui.controller;

import domain.model.DomainException;
import domain.model.Material;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveMaterial extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String material = getService().getMaterial(id).toString() + " was removed";
            request.setAttribute("materialString", material);
            getService().removeMaterial(id);
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals(Role.MODERATOR)) {
                response.sendRedirect("Controller?command=ModOverviewMatPage");
                return "overviewverdeler.jsp";
            } else {
                response.sendRedirect("Controller?command=MaterialsOverview");
                return "materialsoverview.jsp";
            }

        } catch (DomainException e) {
            request.setAttribute("materialString", e.getMessage());
        } catch (NumberFormatException e) {
            request.setAttribute("materialString", "Invalid id");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("materials", getService().getMaterials());
        return "materialsoverview.jsp";
    }
}
