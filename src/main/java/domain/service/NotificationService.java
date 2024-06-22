package domain.service;

import domain.model.Notification;

import java.util.ArrayList;

public interface NotificationService {
    void addNotification(Notification notification);
    ArrayList<Notification> getNotifications(int userId);
    void removeNotification(int notificationId);
}
