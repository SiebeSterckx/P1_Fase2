package ui.controller;

import domain.model.Material;
import domain.model.Message;
import domain.model.Notification;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class SendMessage extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String message = request.getParameter("newMessage");
        String materialId = request.getParameter("materialId");
        User user = (User) request.getSession().getAttribute("user");
        Material material = service.getMaterial(Integer.parseInt(materialId));
        int userId = user.getUserId();
        int providerId = material.getProviderId();
        int distributorId = material.getDistributorId();
        if (userId == providerId) {
            service.addMessage(new Message(material.getId(), userId, distributorId, message));
            service.addNotification(new Notification(distributorId, LocalDateTime.now(), user.getName() + " has sent you a message: " + message));
            request.setAttribute("messages", service.getMessages(material.getId(), userId, distributorId));
        } else if (userId == distributorId) {
            service.addMessage(new Message(material.getId(), userId, providerId, message));
            service.addNotification(new Notification(providerId, LocalDateTime.now(), user.getName() + " has sent you a message: " + message));
            request.setAttribute("messages", service.getMessages(material.getId(), userId, providerId));
        }

        if (userId == providerId) {
            request.setAttribute("chatWith", service.findUserWithId(userId));
        } else if (userId == distributorId) {
            request.setAttribute("chatWith", service.findUserWithId(userId));
        }

        request.setAttribute("materialId", materialId);
        request.setAttribute("material", material);


        return "chat.jsp";

    }
}
