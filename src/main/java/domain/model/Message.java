package domain.model;

import java.time.LocalDateTime;

public class Message {

    int messageId;
    int materialId;
    int fromId;
    int toId;
    String message;
    LocalDateTime dateTime;

    public Message(int messageId, int materialId, int fromId, int toId, String message, LocalDateTime dateTime) {
        setMessageId(messageId);
        setMaterialId(materialId);
        setFromId(fromId);
        setToId(toId);
        setMessage(message);
        setDateTime(dateTime);
    }

    public Message(int materialId, int fromId, int toId, String message) {
        setMaterialId(materialId);
        setFromId(fromId);
        setToId(toId);
        setMessage(message);
        setDateTime(LocalDateTime.now());
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
