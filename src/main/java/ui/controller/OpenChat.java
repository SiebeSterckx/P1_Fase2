package ui.controller;

import domain.model.Material;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenChat extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String materialId = request.getParameter("id");
        User user = (User) request.getSession().getAttribute("user");
        Material material = service.getMaterial(Integer.parseInt(materialId));
        int userId = user.getUserId();
        int providerId = material.getProviderId();
        int distributorId = material.getDistributorId();

        if (userId == providerId) {
            request.setAttribute("chatWith", service.findUserWithId(distributorId));
        } else if (userId == distributorId) {
            request.setAttribute("chatWith", service.findUserWithId(providerId));
        }
        request.getSession().setAttribute("materialId", materialId);
        request.setAttribute("material", material);

        return "chat.jsp";
    }
}

