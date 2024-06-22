package ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Material;
import domain.model.Message;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FetchChat extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        int materialId = Integer.parseInt(request.getSession().getAttribute("materialId").toString());
        User user = (User) request.getSession().getAttribute("user");
        Material material = service.getMaterial(materialId);


        int userId = user.getUserId();
        int providerId = material.getProviderId();
        int distributorId = material.getDistributorId();

        if (userId == providerId) {
            return toJSON(service.getMessages(material.getId(), userId, distributorId));
        } else if (userId == distributorId) {
            return toJSON(service.getMessages(material.getId(), userId, providerId));
        }
        return null;
    }

    private String toJSON(List<Message> messages) {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
