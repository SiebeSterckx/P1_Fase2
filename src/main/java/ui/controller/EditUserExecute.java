package ui.controller;

import domain.model.DomainException;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class EditUserExecute extends RequestHandler {

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
        int id = Integer.parseInt(request.getParameter("id"));
        User user1 = service.findUserWithId(id);
        ArrayList<String> errors = new ArrayList<>();
        User testUser = new User();

        try{
            setEmail(testUser, request, errors);
            setName(testUser, request, errors);
            setRole(testUser, request, errors);
        } catch (DomainException e) {
            errors.add(e.getMessage());
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            return "editUser.jsp";
        }
        if (errors.size() == 0){
            System.out.println(request.getParameter("sortby"));
            service.editUser(id, request.getParameter("email"), Role.valueOf(request.getParameter("sortby").toUpperCase()), request.getParameter("name"));
        }
        return "Controller?command=Home";
    }

    public void setRole(User user, HttpServletRequest request, ArrayList<String> errors){
        String role = request.getParameter("sortby");
        try{
            if (role == null || role.isEmpty()) {
                errors.add("No role given");
            } else{
                role = role.toUpperCase();
            System.out.println(role);
            user.setRole(Role.valueOf(role));
            }
        } catch (DomainException e) {
            errors.add("Invalid role");
        } catch (NumberFormatException e) {
            errors.add("Invalid role");
        }

    }

    public void setEmail(User user, HttpServletRequest request, ArrayList<String> errors){
        String email = request.getParameter("email");
        try {
            if (email == null || email.isEmpty()) {
                errors.add("No email given");
            } else {

                user.setEmail(email);
                request.setAttribute("emailPreviousValue", email);
            }
            } catch(DomainException e){
                errors.add("Invalid email");
            } catch(NumberFormatException e){
                errors.add("Invalid email");
            }

    }

    public void setName(User user, HttpServletRequest request, ArrayList<String> errors){
        String name = request.getParameter("name");
        try{
            if (name == null || name.isEmpty()) {
                errors.add("No name given");
            } else {


            user.setCompanyName(name);
            request.setAttribute("namePreviousValue", name);
            }
        } catch (DomainException e) {
            errors.add("Invalid name");
        } catch (NumberFormatException e) {
            errors.add("Invalid name");
        }
    }
}