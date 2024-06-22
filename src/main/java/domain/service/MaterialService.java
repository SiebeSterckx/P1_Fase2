package domain.service;

import domain.model.Material;
import domain.model.User;

import java.util.ArrayList;

public interface MaterialService {
    void addMaterial(Material material);

    ArrayList<Material> getMaterials();

    void removeMaterial(int id);

    Material getMaterial(int id);

    ArrayList<Material> getMaterialsForVerdeler(User verdeler);

    void acceptMaterial(String id);

    void rejectMaterial(String id, String message);

    void addMaterialToVerdeler(Material material);

    ArrayList<Material> getMaterialsForAanbieder(int user);

    void updateMaterialToVerdeler(String verdeler, int id);
}

