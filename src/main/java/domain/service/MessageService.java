package domain.service;

import domain.model.Message;

import java.util.ArrayList;

public interface MessageService {
    void addMessage(Message message);
    ArrayList<Message> getMessages();
    ArrayList<Message> getMessages(int materialId, int fromId, int toId);
}
