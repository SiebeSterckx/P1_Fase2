package domain.service;

import domain.model.Message;

import java.util.ArrayList;

public class MessageServiceInMemory implements MessageService {
    private ArrayList<Message> messages = new ArrayList<>();

    @Override
    public void addMessage(Message message) {
        messages.add(message);
        message.setMessageId(getNextId());
    }

    @Override
    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public ArrayList<Message> getMessages(int materialId, int userId1, int userId2) {
        ArrayList<Message> messages = new ArrayList<>();
        for (Message message : this.messages) {
            if (message.getMaterialId() == materialId && message.getFromId() == userId1 && message.getToId() == userId2) {
                messages.add(message);
            }
            if (message.getMaterialId() == materialId && message.getFromId() == userId2 && message.getToId() == userId1) {
                messages.add(message);
            }
        }
        return messages;
    }

    public int getNextId() {
        int nextId = 0;
        for (Message message : messages) {
            if (message.getMessageId() > nextId) {
                nextId = message.getMessageId();
            }
        }
        return nextId + 1;
    }
}
