package domain.service;


import domain.model.Message;

import domain.model.Notification;

import domain.model.Role;

import domain.model.User;

import domain.model.Material;

import java.util.ArrayList;

public class AppService {

    private MaterialService materials = new MaterialServiceDBSQL();

    private UserService users = new UserServiceDBSQL();

    private MessageService messages = new MessageServiceDBSQL();


    private NotificationService notifications = new NotificationServiceDBSQL();

    private AwaitingUserService awaitingUsers = new AwaitingUserServiceDBSQL();



    public void addUser(User user) {
        users.addUser(user);
    }

    public void removeUser(int user) {
        users.removeUser(user);
    }

    public User findUserWithEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("pls fill in your email");
        }
        return users.findUserWithEmail(email);
    }

    public User findUserWithId(int id) {
        return users.findUserWithId(id);
    }

    public ArrayList<User> getAllDistributors() {
        return users.getAllDistributors();
    }

    public ArrayList<User> getAllProviders() {
        return users.getAllProviders();
    }

    public ArrayList<User> getAllUsers() {
        return users.getAllUsers();
    }

    /* materials */

    public void addMaterial(Material material) {
        materials.addMaterial(material);
    }


    public ArrayList<Material> getMaterials() {
        return materials.getMaterials();
    }

    public void updateMaterialToVerdeler(String verdeler, int id) {
        materials.updateMaterialToVerdeler(verdeler, id);
    }


    public void removeMaterial(int id) {
        materials.removeMaterial(id);
    }

    public Material getMaterial(int id) {
        return materials.getMaterial(id);
    }

    public ArrayList<Material> getMaterialsForVerdeler(User verdeler) {
        return materials.getMaterialsForVerdeler(verdeler);
    }

    public void acceptMaterial(String id) {
        materials.acceptMaterial(id);
    }

    public void rejectMaterial(String id, String message) {
        materials.rejectMaterial(id, message);
    }

    public void addMessage(Message message) {
        messages.addMessage(message);
    }

    public ArrayList<Message> getMessages() {
        return messages.getMessages();
    }

    public ArrayList<Message> getMessages(int materialId, int fromId, int toId) {
        return messages.getMessages(materialId, fromId, toId);
    }

    public void addMaterialToVerdeler(Material material) {
        materials.addMaterialToVerdeler(material);
    }

    public ArrayList<Material> getMaterialsForAanbieder(int userid) {
        return materials.getMaterialsForAanbieder(userid);
    }
    /* Methods from AwaitingUserService */

    public void addAwaitingUser(User user) {
        awaitingUsers.addUser(user);
    }
    public User findAwaitingUserWithEmail(String email) {
        return awaitingUsers.findUserWithEmail(email);
    }

    public User findAwaitingUserWithId(int id) {
        return awaitingUsers.findUserWithId(id);
    }

    public ArrayList<User> getAllAwaitingDistributors() {
        return awaitingUsers.getAllDistributors();
    }

    public ArrayList<User> getAllAwaitingProviders() {
        return awaitingUsers.getAllProviders();
    }

    public ArrayList<User> getAllAwaitingUsers() {
        return awaitingUsers.getAllUsers();
    }

    public void removeAwaitingUser(int user) {
        awaitingUsers.removeUser(user);
    }

    public void editUser(int id, String email, Role role, String name) {
        users.editUser(id, email, role, name);
    }

    public void addNotification(Notification notification) {
        notifications.addNotification(notification);
    }

    public ArrayList<Notification> getNotifications(int userId) {
        return notifications.getNotifications(userId);
    }

    public void removeNotification(int notificationId) {
        notifications.removeNotification(notificationId);
    }
}
