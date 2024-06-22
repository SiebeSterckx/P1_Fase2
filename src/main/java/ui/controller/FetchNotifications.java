package ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Notification;
import domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FetchNotifications extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "";
        }
        return toJSON(user);
    }

    public String toJSON(User user) {
        ArrayList<Notification> notifications = service.getNotifications(user.getUserId());
        ArrayList<Notification> reversed = new ArrayList<>();
        for (int i = notifications.size() - 1; i >= 0; i--) {
            reversed.add(notifications.get(i));
        }
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
            result = mapper.writeValueAsString(reversed);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}

