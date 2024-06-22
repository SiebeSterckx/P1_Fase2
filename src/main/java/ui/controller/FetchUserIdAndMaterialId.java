package ui.controller;

import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchUserIdAndMaterialId extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserId();
        int materialId = Integer.parseInt(request.getSession().getAttribute("materialId").toString());

        return toJSON(userId, materialId);
    }

    private String toJSON(int userId, int materialId) {
        return  "{\"userId\":" + userId + ",\"materialId\":" + materialId + "}";
    }


}
