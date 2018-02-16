package app.games.dim.animallab.model;

/**
 * Created by Igor on 09/02/2018.
 */

public class Beast {

    private String name;
    private int physicalHealth;
    private int mentalHealth;
    private int stress;
    private int hunger;

    public Beast(){
        this.name = "SpecimenXA392";
        this.mentalHealth = 70;
        this.physicalHealth = 75;
        this.stress = 10;
        this.hunger = 60;
    }

    public String getName () {
        return this.name;
    }

    public int getPhysicalHealth() {
        return physicalHealth;
    }

    public void setPhysicalHealth(int physicalHealth) {
        this.physicalHealth = physicalHealth;
    }

    public int getMentalHealth() {
        return mentalHealth;
    }

    public void setMentalHealth(int mentalHealth) {
        this.mentalHealth = mentalHealth;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }
}
