package ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class sendOtherDistributor extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            service.updateMaterialToVerdeler(request.getParameter("choose"), Integer.parseInt(request.getParameter("materialId")));
            HttpSession session = request.getSession();
            session.setAttribute("success", "Material sent to other distributor");
            response.sendRedirect("Controller?command=OverviewVerdeler");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "overviewverdeler.jsp";
    }
}
