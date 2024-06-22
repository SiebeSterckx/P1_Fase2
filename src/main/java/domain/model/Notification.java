package domain.model;

import java.time.LocalDateTime;

public class Notification {

    int notificationId;
    private int userId;
    private LocalDateTime dateTime;
    private String message;

    public Notification(int userId, LocalDateTime dateTime, String message) {
        setUserId(userId);
        setDateTime(dateTime);
        setMessage(message);
    }

    public Notification(int notificationId, int userId, LocalDateTime dateTime, String message) {
        setNotificationId(notificationId);
        setUserId(userId);
        setDateTime(dateTime);
        setMessage(message);
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
