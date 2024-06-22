package domain.model;

import java.io.File;
import java.time.LocalDate;

public class Material {

    private int id;
    //todo set this in add
    private int providerId ;
    private int distributorId ;
    private String type;
    private LocalDate lastPickupDate;
    private String address;
    private State status;
    private File image;
    private String messageReject;

    private String img;

    public Material(String type, LocalDate lastPickupDate, String address, State status, File file, int providerId, int distributorId, int id) {
        setType(type);
        setLastPickupDate(lastPickupDate);
        setAddress(address);
        setStatus(status);
        setFile(file);
        setProviderId(providerId);
        setDistributorId(distributorId);
        messageReject = "";
        setId(id);

    }

    public Material(){
        messageReject = "";
    }

    public Material(String type, LocalDate lastPickupDate, String location, State status, int providerId, int distributorId, int id) {
        setType(type);
        setLastPickupDate(lastPickupDate);
        setAddress(location);
        setStatus(status);
        setProviderId(providerId);
        setDistributorId(distributorId);
        setId(id);

    }

    public Material(String type, LocalDate lastPickupDate, String location, State status, int providerId, int distributorId, int id, String messageReject) {
        setType(type);
        setLastPickupDate(lastPickupDate);
        setAddress(location);
        setStatus(status);
        setProviderId(providerId);
        setDistributorId(distributorId);
        setId(id);
        this.messageReject = messageReject;

    }
    public Material(String type, LocalDate lastPickupDate, String location, State status, int providerId, int distributorId, int id, String messageReject, String image) {
        setType(type);
        setLastPickupDate(lastPickupDate);
        setAddress(location);
        setStatus(status);
        setProviderId(providerId);
        setDistributorId(distributorId);
        setId(id);
        this.messageReject = messageReject;
        setImg(image);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getLastPickupDate() {
        return lastPickupDate;
    }

    public String getLastPickupDateAsString() {

        //in format dd/mm/yyyy with simple date format
        return lastPickupDate.toString();
    }

    public void setLastPickupDate(LocalDate lastPickupDate) {
        this.lastPickupDate = lastPickupDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public State getStatus() {
        return status;
    }

    public String getStatusAsString() {
        return status.getName();
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getMessageReject() {
        return messageReject;
    }

    public void setMessageReject(String messageReject) {
        if(messageReject == null || messageReject.trim().isEmpty()){
            throw new DomainException("Reject message cannot be empty.");
        } else {
            this.messageReject = messageReject;
        }
    }

    public void setImg(String img){
        this.img = img;
    }

    public String getImg(){

        return img;
    }


    @Override
    public String toString() {
        return "Material: " + type + " " + lastPickupDate + " " + address + " " + status;
    }
    public void setFile(File file) {
        this.image = file;
    }

    public String getLocation() {
return address;

    }

    public String getFile() {
        return image.getPath();
    }
}