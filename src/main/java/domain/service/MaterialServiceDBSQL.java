package domain.service;

import domain.model.Material;
import domain.model.State;
import domain.model.User;
import util.DbConnectionService;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MaterialServiceDBSQL implements MaterialService {
    private final Connection connection;
    private final String schema;

    public MaterialServiceDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
    }

    /**
     * Check the connection and reconnect when necessary
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        return this.connection;
    }

    public void addImageToDatabase(String fileName) {
        String query = String.format("INSERT into %s.image (name) values (?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, fileName);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void addMaterial(Material material) {
        String query = String.format("INSERT into %s.material (providerId,distributorId,type,lastPickupDate,location,status,imagepath) values (?, ?, ?, ?, ?, ?, ?)", schema);
        try {
                    PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, material.getProviderId());
            preparedStatement.setInt(2, material.getDistributorId());
            preparedStatement.setString(3, material.getType());
            preparedStatement.setDate(4, Date.valueOf(material.getLastPickupDate()));
            preparedStatement.setString(5, material.getLocation());
            preparedStatement.setString(6, material.getStatus().toString());
            preparedStatement.setInt(7, Integer.parseInt("1"));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void addMaterialToVerdeler(Material material) {
        String query = String.format("INSERT into %s.material (providerId,distributorId,type,lastPickupDate,location,status,imagepath) values (?, ?, ?, ?, ?, ?, ?)", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, material.getProviderId());
            preparedStatement.setInt(2, material.getDistributorId());
            preparedStatement.setString(3, material.getType());
            preparedStatement.setDate(4, Date.valueOf(material.getLastPickupDate()));
            preparedStatement.setString(5, material.getLocation());
            preparedStatement.setString(6, material.getStatus().toString());
            preparedStatement.setString(7, material.getImg());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void updateMaterialToVerdeler(String verdeler, int id) {
        int distributorId;
        switch(verdeler) {
            case "vites":
                distributorId = 4;
                break;
            case "materialenbank":
                distributorId = 5;
                break;
            case "scholen 20-30":
                distributorId = 6;
                break;
            default:
                throw new DbException("Verdeler not found");
        }
        String query = String.format("UPDATE %s.material SET distributorid = ? WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, distributorId);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Material> getMaterialsForAanbieder(int userid) {
        String query = String.format("SELECT * FROM %s.material WHERE providerId = ? order by 1", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userid);
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Material> materials = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                int providerId = result.getInt("providerId");
                int distributorId = result.getInt("distributorId");
                String type = result.getString("type");
                LocalDate lastPickupDate = result.getDate("lastPickupDate").toLocalDate();
                String location = result.getString("location");
                State status = State.valueOf(result.getString("status").toUpperCase());
                String path = result.getString("imagepath");
                System.out.println( "path: " + path);
                String message = result.getString("rejectmessage");
                materials.add(new Material(type,lastPickupDate,location,status,providerId,distributorId,id, message, path));
            }
            return materials;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Material> getMaterials() {
        String query = String.format("SELECT * FROM %s.material ORDER BY id", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();
            ArrayList<Material> materials = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                int providerId = result.getInt("providerId");
                int distributorId = result.getInt("distributorId");
                String type = result.getString("type");
                LocalDate lastPickupDate = result.getDate("lastPickupDate").toLocalDate();
                String location = result.getString("location");
                State status = State.valueOf(result.getString("status").toUpperCase());
                String path = result.getString("imagepath");
                String message = result.getString("rejectmessage");
                materials.add(new Material(type,lastPickupDate,location,status,providerId,distributorId,id, message,path));

            }
            return materials;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }



    }

    @Override
    public void removeMaterial(int id) {
        String query = String.format("DELETE FROM %s.material WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public Material getMaterial(int id) {
        String query = String.format("SELECT * FROM %s.material WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next())
            {
                int id1 = result.getInt("id");
                int providerId = result.getInt("providerId");
                int distributorId = result.getInt("distributorId");
                String type = result.getString("type");
                Date lastPickupDate = result.getDate("lastPickupDate");
                String location = result.getString("location");
                String status = result.getString("status");
                String message = result.getString("rejectmessage");


                State state = State.valueOf(status.toUpperCase());


                return new Material(type,lastPickupDate.toLocalDate(),location,state,providerId,distributorId,id1, message);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;


    }

    @Override
    public ArrayList<Material> getMaterialsForVerdeler(User verdeler) {
        ArrayList<Material> materials = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.material WHERE distributorId = ? order by id", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, verdeler.getUserId());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int providerId = result.getInt("providerId");
                int distributorId = result.getInt("distributorId");
                String type = result.getString("type");
                LocalDate lastPickupDate = result.getDate("lastPickupDate").toLocalDate();
                String location = result.getString("location");
                State status = State.valueOf(result.getString("status").toUpperCase());
                String message = result.getString("rejectmessage");
                String imagepath = result.getString("imagepath");

                materials.add(new Material(type,lastPickupDate,location,status,providerId,distributorId,id, message,imagepath));
            }
            return materials;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void acceptMaterial(String id) {
        String query = String.format("UPDATE %s.material SET status = ? WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, "ACCEPTED");
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }


    }

    @Override
    public void rejectMaterial(String id, String message) {
        String query = String.format("UPDATE %s.material SET status = ?, rejectmessage = ? WHERE id = ?", schema);
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, "REJECTED");
            preparedStatement.setInt(3, Integer.parseInt(id));
            preparedStatement.setString(2, message);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }
}
