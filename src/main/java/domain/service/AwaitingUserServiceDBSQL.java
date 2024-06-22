package domain.service;

import domain.model.DomainException;
import domain.model.Role;
import domain.model.User;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AwaitingUserServiceDBSQL implements AwaitingUserService{

    private final Connection connection;
    private final String schema;

    public AwaitingUserServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    /**
     * Check the connection and reconnect when necessary
     * @return the connection with the db, if there is one
     *
     */
    private Connection getConnection() {
        return this.connection;
    }
    @Override
    public void addUser(User user) {
        String query = String.format("insert into %s.awaitinguser (email,password,role,name) values (?,?,?,?)", this.schema);
        if (findUserWithEmail(user.getEmail()) != null) {
            throw new DomainException("User already exists");
        }
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setString(4, user.getCompanyName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public User findUserWithEmail(String email) {
        String query = String.format("SELECT * FROM %s.awaitinguser WHERE email = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String email1 = result.getString("email");
                String password = result.getString("password");

                String role = result.getString("role");
                String name = result.getString("name");

                Role role1 = Role.valueOf(role);
                return new User(id, email1, password, role1,name);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;

    }

    @Override
    public User findUserWithId(int id) {
        String query = String.format("SELECT * FROM %s.awaitinguser WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int id1 = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                String role = result.getString("role");
                Role role1 = Role.valueOf(role);
                return new User(id1, email, password, role1, name);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;
    }



    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.awaitinguser order by id" , schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                String role = result.getString("role");
                Role role1 = Role.valueOf(role);
                users.add(new User(id, email, password, role1, name));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
    }

    @Override
    public void removeUser(int user) {
        String query = String.format("DELETE FROM %s.awaitinguser WHERE id = ?", schema);

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, user);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> getAllDistributors() {
        ArrayList<User> users = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.awaitinguser WHERE role = 'VERDELER' order by id" , schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                String role = result.getString("role");
                Role role1 = Role.valueOf(role);
                users.add(new User(id, email, password, role1, name));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
    }

    @Override
    public ArrayList<User> getAllProviders() {
        ArrayList<User> users = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.awaitinguser WHERE role = 'AANBIEDER' order by id" , schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("email");
                String password = result.getString("password");
                String name = result.getString("name");
                String role = result.getString("role");
                Role role1 = Role.valueOf(role);
                users.add(new User(id, email, password, role1, name));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return users;
    }
}
