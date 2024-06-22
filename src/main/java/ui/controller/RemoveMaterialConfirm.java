package ui.controller;

import domain.model.DomainException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RemoveMaterialConfirm extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("id", id);
            String material = getService().getMaterial(id).toString();
            request.setAttribute("variable", "removeMaterial");
        } catch (DomainException e){
            request.setAttribute("materialString", e.getMessage());
        } catch (NumberFormatException e){
            request.setAttribute("materialString", "Invalid id");
        }
        return "confirmAction.jsp";

    }
}
