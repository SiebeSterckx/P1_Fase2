package ui.controller;

import domain.model.Material;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class MaterialsOverview extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        User u = (User) session.getAttribute("user");
        request.setAttribute("distributors", service.getAllDistributors());

        ArrayList<Material> materials = service.getMaterialsForAanbieder(u.getUserId());


        request.setAttribute("materials", service.getMaterialsForAanbieder(u.getUserId()));
        return "materialsoverview.jsp";
    }
}