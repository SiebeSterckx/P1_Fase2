package domain.model;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;

public class User {

    private int userId;
    private String email;
    private String password;
    private Role role;
    private final ArrayList<Material> materials;
    private String companyName;

    public User() {
        this.materials = new ArrayList<>();
    }


    public User(int userId, String email, String password, Role role) {

        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = role;
        this.materials = new ArrayList<>();
    }

    public User(int userId, String email, String password, Role role, String companyName) {
        this(userId, email, password, role);
        this.companyName = companyName;
    }


    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        if(email == null || email.trim().isEmpty()) throw new DomainException("Email cannot be empty");
        this.email = email;
    }

    public void setPassword(String password) {
        if(password == null || password.trim().isEmpty()) throw new DomainException("Password cannot be empty");
        this.password = hashPassword(password);
    }

    public void setRole(Role role) {
        this.role = role;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if(companyName == null || companyName.trim().isEmpty()) throw new DomainException("Company name cannot be empty");
        this.companyName = companyName;
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public String getName() {
        String result = email.substring(0, email.indexOf("@"));
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

    private String hashPassword(String password) {
        try {
            //create MessageDigest
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            //reset
            crypt.reset();
            //update
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            //digest
            byte[] digest = crypt.digest();
            //convert to String
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            //return hashed password
            return digestAsBigInteger.toString(16);

        }
        catch (Exception e) {
            throw new DomainException("Password could not be hashed");
        }

    }
}


