package ui.controller;

import domain.model.DomainException;

import domain.model.Notification;

import domain.model.Role;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class RejectMaterial extends RequestHandler{
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

        try{
            String value = request.getParameter("flexRadioDefault");
            String mes = "";
            if (value.trim().isEmpty()){
                request.setAttribute("message", "Please fill in a message");
                return "confirmAction.jsp";
            }

            switch (value) {
                case "Own text":
                    mes = request.getParameter("radioTextField");
                    break;
                default:
                    mes = value;
                    break;
            }
            int id = Integer.parseInt(request.getParameter("id"));
            service.rejectMaterial(String.valueOf(id), mes);
            service.addNotification(new Notification(service.getMaterial(id).getProviderId(), LocalDateTime.now(), "Your material has been rejected"));
            request.setAttribute("materials", service.getMaterialsForVerdeler(user));
            return "overviewverdeler.jsp";
        } catch (DomainException exc){
            request.setAttribute("message", exc.getMessage());
            request.setAttribute("id", request.getParameter("id"));
            request.setAttribute("variable", "rejectMaterial");
            return "confirmAction.jsp";
        }

    }

}