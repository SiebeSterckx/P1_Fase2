package domain.service;

import domain.model.Notification;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;

public class NotificationServiceDBSQL implements NotificationService {

    private final Connection connection;
    private final String schema;

    public NotificationServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }


    @Override
    public void addNotification(Notification notification) {
        String query = String.format("INSERT into %s.notification (userId, dateTime, message) values (?, ?, ?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, notification.getUserId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(notification.getDateTime()));
            preparedStatement.setString(3, notification.getMessage());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Notification> getNotifications(int userId) {
        ArrayList<Notification> notifications = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.notification WHERE userId = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int notificationId = result.getInt("notificationId");
                int userId1 = result.getInt("userId");
                Timestamp dateTime = result.getTimestamp("dateTime");
                String message = result.getString("message");
                notifications.add(new Notification(notificationId, userId1, dateTime.toLocalDateTime(), message));
            }
            return notifications;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void removeNotification(int notificationId) {
        String query = String.format("DELETE FROM %s.notification WHERE notificationId = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, notificationId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
