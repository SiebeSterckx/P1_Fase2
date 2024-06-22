package ui.controller;

import domain.model.DomainException;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;

public class Login extends RequestHandler {
    private ArrayList<User> users;

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // if the session user is not null, redirect to the index page

        ArrayList<String> errors = new ArrayList<>();
        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("password");
        String passwordH = hashPassword(password);


        try{
            if (email == null || email.isEmpty()) {
                System.out.println("email is empty");
                throw new DomainException("Please fill in your email and password");
            }

            User user = service.findUserWithEmail(email);
            if (user == null){
                System.out.println("user is null");
                throw new DomainException("User not found/Credentials are incorrect");
            }
            if (password == null || password.isEmpty()) {
                System.out.println("password is empty");
                errors.add("Please fill in your email password");
            }



            if(user.isCorrectPassword(passwordH)){
                session.setAttribute("user", user);
                return "Controller?command=Home";
            }
            else{
                errors.add("Incorrect password");
                request.setAttribute("errors", errors);
                request.setAttribute("emailPreviousValue", email);
                return "index.jsp";
            }
        }
        catch (Exception e){
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.setAttribute("emailPreviousValue", email);

            return "index.jsp";
        }
        //get the users from userService class with the getAll method


    }

    private String hashPassword(String password) {
        try {
            //create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            //reset
            crypt.reset();
            //update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            //digest
            byte[] digest = crypt.digest();
            //convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            //return hashed password
            return digestAsBigInteger.toString(16);

        }
        catch (Exception e) {
            throw new DomainException("Password could not be hashed");
        }

    }
}
