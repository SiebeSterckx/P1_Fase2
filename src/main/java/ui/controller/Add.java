package ui.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Add extends RequestHandler {
    Material material;

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("message", "You must be logged in to access this page");
            return "index.jsp";
        }

        ArrayList<String> errors = new ArrayList<>();
        Material material = new Material();
        setImg(material, request, errors);
        setDate(material, request, errors);
        setLocation(material, request, errors);
        setCategory(material, request, errors);
        setDistributorId(material, request, errors);
        setProviderid(material, request, errors);
        material.setStatus(State.PENDING);
        if (errors.size() == 0) {
            try {
                service.addMaterialToVerdeler(material);
                service.addNotification(new Notification(material.getDistributorId(), LocalDateTime.now(), "You have a new material (" + material.getType() + "to accept/reject"));
                response.sendRedirect("Controller?command=OverviewVerdeler");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("materials", service.getMaterialsForAanbieder(material.getProviderId()));


            return "materialsoverview.jsp";
        } else {
            request.setAttribute("errors", errors);
            return "add.jsp";
        }

    }


    /* public void setImg(Material material, HttpServletRequest request, ArrayList<String> errors) {

        try {
            Part filePart = request.getPart("img");
            System.out.println("filepart Object:   " + filePart);
            // check if filepart is empty
            if (filePart.getSize() == 0) {
                throw new IllegalArgumentException("No file selected");
            }

            System.out.println(filePart);
            String fileName = filePart.getSubmittedFileName();
            String imagePath = request.getServletContext().getRealPath("img/");
            if (!new File(imagePath + "/" + fileName).exists()) {
                File myfile = new File(imagePath + "/" + fileName);
                System.out.println("file: " + myfile);
                myfile.createNewFile();
            }
            System.out.println("size:   " + request.getParts().size());
            for (Part part : request.getParts()) {
                part.write(imagePath + fileName);
                System.out.println("File actual written path:  " + imagePath + fileName);
            }
            material.setFile(new File(imagePath + "/" + fileName));
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }

    }


     */
    public void setImg(Material material, HttpServletRequest request, ArrayList<String> errors) {
        try {
            Part filePart = request.getPart("img");
            // check if filepart is empty
            if (filePart.getSize() == 0) {
                throw new DomainException("No file selected");
            }

            // get the file name and input stream of the uploaded file
            String fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();

            // get the real path of the img directory
            String imagePath = request.getServletContext().getRealPath("/");
            System.out.println(imagePath);
            material.setImg(fileName);

            // create the img directory if it doesn't exist
            File imgDir = new File(imagePath);
            if (!imgDir.exists()) {
                imgDir.mkdirs();
            }

            // create the file in the img directory
            File file = new File(imagePath + "/img/" + File.separator + fileName);

            // copy the uploaded file to the img directory
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // set the file on the material object
            material.setFile(file);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException | DomainException e) {
            errors.add(e.getMessage());
        }
    }

    public void setLocation(Material material, HttpServletRequest request, ArrayList<String> errors) {
        String location = request.getParameter("location");
        request.setAttribute("locationPreviousValue", location);
        try{
            if (location == null || location.trim().isEmpty()){
                throw new DomainException("No location given");
            }
            material.setAddress(location);
        } catch (DomainException |IllegalArgumentException exc){
            errors.add(exc.getMessage());
        }
    }

    public void setDate(Material material, HttpServletRequest request, ArrayList<String> errors) {
        String date = request.getParameter("LastDate");
        request.setAttribute("datePreviousValue", date);
        try {
            if (date == null || date.isEmpty()) {
                throw new DomainException("Please fill in a date");
            }
            LocalDate date1 = LocalDate.parse(date);
            if (date1.isBefore(LocalDate.now())) {
                throw new DomainException("Date must be in the future");
            }
            material.setLastPickupDate(date1);

        } catch (DomainException e){
            errors.add(e.getMessage());
        }


    }

    public void setCategory(Material material, HttpServletRequest request, ArrayList<String> errors) {
        String category = request.getParameter("category");
        request.setAttribute("categoryPreviousValue", category);
        try{
            if (category == null || category.trim().isEmpty()){
                throw new DomainException("No category given");
            }
            material.setType(category);
        } catch (DomainException exc){
            errors.add(exc.getMessage());
        }
        material.setType(category);
    }

    public void setDistributorId(Material material, HttpServletRequest request, ArrayList<String> errors) {
        String Verdeler = request.getParameter("sortby");
        switch (Verdeler) {
            case "Vites":
                material.setDistributorId(4);
                break;

            case "Materialenbank":
                material.setDistributorId(5);
                break;
            case "Scholen 20-30":
                material.setDistributorId(6);
                break;
        }


    }

    public void setProviderid(Material material, HttpServletRequest request, ArrayList<String> errors) {
        User u = (User) request.getSession().getAttribute("user");
        material.setProviderId(u.getUserId());
    }

}
