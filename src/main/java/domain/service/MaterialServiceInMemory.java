package domain.service;

import domain.model.DomainException;
import domain.model.Material;
import domain.model.State;
import domain.model.User;

import java.util.ArrayList;

public class MaterialServiceInMemory implements MaterialService {

    private ArrayList<Material> materials;

/*
    public MaterialServiceInMemory() {
        materials = new ArrayList<>();

        Material material1 = new Material("plastic", LocalDate.of(2023,12,12), "Gent", State.PENDING, new File("img/WBS.PNG"), 1, 2);
        Material material2 = new Material("paper",LocalDate.of(2023,12,12), "Gent", State.PENDING,new File("img/WBS.PNG"), 1, 2);
        Material material3 = new Material("glass", LocalDate.of(2023,12,12), "Gent", State.REJECTED,new File("img/WBS.PNG"), 1, 2);
        Material material4 = new Material("metal", LocalDate.of(2023,12,12), "Gent", State.PENDING,new File("img/WBS.PNG"), 1, 2);
        Material material5 = new Material("plastic", LocalDate.of(2023,12,12), "Gent", State.PENDING,new File("img/WBS.PNG"), 1, 2);

        addMaterial(material1);
        addMaterial(material2);
        addMaterial(material3);
        addMaterial(material4);
        addMaterial(material5);
    }
*/

    public void addMaterial(Material material) {
        material.setId(getNextId());
        materials.add(material);
    }

    private int getNextId() {
        int nextId = 0;
        for (Material material : materials) {
            if (material.getId() > nextId) {
                nextId = material.getId();
            }
        }
        return nextId + 1;
    }

    public ArrayList<Material> getMaterials() {
        return materials;
    }


    public void removeMaterial(int id) {
        for (Material material : materials) {
            if (material.getId() == id) {
                if(material.getStatus() == State.ACCEPTED) {
                    throw new DomainException("Material is already accepted");
                }
                materials.remove(material);
                return;
            }
        }
        throw new DomainException("Material does not exist");
    }

    public Material getMaterial(int id) {
        for (Material material : materials) {
            if (material.getId() == id) {
                return material;
            }
        }
        throw new DomainException("Material does not exist");
    }

    public ArrayList<Material> getMaterialsForVerdeler(User verdeler) {
        ArrayList<Material> materialsForVerdeler = new ArrayList<>();
        for (Material material : materials) {
            if (material.getDistributorId() == verdeler.getUserId()) {
                materialsForVerdeler.add(material);
            }
        }
        return materialsForVerdeler;
    }

    public void acceptMaterial(String id) {
        for (Material material : materials) {
            if (material.getId() == Integer.parseInt(id)) {
                material.setStatus(State.ACCEPTED);
                return;
            }
        }
        throw new DomainException("Material does not exist");
    }

    @Override
    public void rejectMaterial(String id, String message) {

    }

    public void rejectMaterial(String id) {
        for (Material material : materials) {
            if (material.getId() == Integer.parseInt(id)) {
                material.setStatus(State.REJECTED);
                return;
            }
        }
        throw new DomainException("Material does not exist");
    }

    @Override
    public void addMaterialToVerdeler(Material material) {

    }

    @Override
    public ArrayList<Material> getMaterialsForAanbieder(int user) {
        return null;
    }

    @Override
    public void updateMaterialToVerdeler(String verdeler, int id) {

    }
}
