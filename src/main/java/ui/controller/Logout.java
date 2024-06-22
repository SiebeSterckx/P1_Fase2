package ui.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        // delete the session user
        session.removeAttribute("user");

        try {
            response.sendRedirect("Controller?command=Home");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Controller?command=Home";

    }
}


