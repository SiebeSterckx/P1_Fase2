package domain.service;

import domain.model.Message;
import util.DbConnectionService;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageServiceDBSQL implements MessageService {

    private final Connection connection;
    private final String schema;

    public MessageServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    @Override
    public void addMessage(Message message) {
        String query = String.format("INSERT INTO %s.messages (materialId, fromId, toId, message, dateTime) VALUES (?, ?, ?, ?, ?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, message.getMaterialId());
            statement.setInt(2, message.getFromId());
            statement.setInt(3, message.getToId());
            statement.setString(4, message.getMessage());
            statement.setTimestamp(5, Timestamp.valueOf(message.getDateTime()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Message> getMessages() {
        ArrayList<Message> messages = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.messages", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int messageId = result.getInt("messageId");
                int materialId = result.getInt("materialId");
                int fromId = result.getInt("fromId");
                int toId = result.getInt("toId");
                String message = result.getString("message");
                LocalDateTime dateTime = result.getTimestamp("dateTime").toLocalDateTime();
                messages.add(new Message(messageId, materialId, fromId, toId, message, dateTime));
            }
            return messages;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Message> getMessages(int materialId, int fromId, int toId) {
        ArrayList<Message> messages = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.messages WHERE materialId = ? AND ((toId = ? AND fromId = ?) OR (toId = ? AND fromId = ?))", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, materialId);
            preparedStatement.setInt(2, toId);
            preparedStatement.setInt(3, fromId);
            preparedStatement.setInt(4, fromId);
            preparedStatement.setInt(5, toId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int messageId = result.getInt("messageId");
                int materialId1 = result.getInt("materialId");
                int fromId1 = result.getInt("fromId");
                int toId1 = result.getInt("toId");
                String message = result.getString("message");
                LocalDateTime dateTime = result.getTimestamp("dateTime").toLocalDateTime();
                messages.add(new Message(messageId, materialId1, fromId1, toId1, message, dateTime));
            }
            return messages;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Connection getConnection() {
        return this.connection;
    }
}
