package ui.controller;

import domain.model.DomainException;
import domain.model.Role;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String destination ="Controller?command=Home";
        String faultyDestination = "register.jsp";

        ArrayList<String> errors = new ArrayList<>();

        User user = new User();

        setEmailRegister(user, request, errors);
        setPasswordRegister(user, request, errors);
        setCompanyNameRegister(user, request, errors);
        user.setRole(Role.AANBIEDER);

        if (errors.size() == 0) {
            try {
                service.addAwaitingUser(user);
                request.getSession().setAttribute("successRegister", "You have successfully registered, you will be notified when your account is approved");
                response.sendRedirect(destination);
                return "";
            } catch (DomainException | IOException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return faultyDestination;

    }

    private void setEmailRegister(User user, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email").toLowerCase();
        boolean emailHasErrors = false;
        try {
            if (service.findUserWithEmail(email) != null) {
                errors.add("Email already exists");
                emailHasErrors = true;
            }
            request.setAttribute("emailPreviousValueRegister", email);
            user.setEmail(email);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            emailHasErrors = true;
        }
        finally {
            request.setAttribute("emailHasErrors", emailHasErrors);
        }
    }

    private void setPasswordRegister(User user, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        boolean passwordHasErrors = false;
        try {
            request.setAttribute("passwordPreviousValueRegister", password);
            user.setPassword(password);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            passwordHasErrors = true;
        } finally {
            request.setAttribute("passwordHasErrors", passwordHasErrors);
        }
    }
    private void setCompanyNameRegister(User user, HttpServletRequest request, ArrayList<String> errors) {
        String companyName = request.getParameter("companyname");
        boolean companyNameHasErrors = false;
        try {
            request.setAttribute("companyNamePreviousValueRegister", companyName);
            user.setCompanyName(companyName);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            companyNameHasErrors = true;
        } finally {
            request.setAttribute("companyNameHasErrors", companyNameHasErrors);
        }
    }

}
