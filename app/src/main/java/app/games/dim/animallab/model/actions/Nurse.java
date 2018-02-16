package app.games.dim.animallab.model.actions;

import app.games.dim.animallab.R;
import app.games.dim.animallab.model.GameController;

/**
 * Created by Igor on 14/02/2018.
 */

public class Nurse extends AAction {

    private int nameId;

    private int units;
    private double price;
    private long duration;


    public Nurse(){
        this.nameId = R.string.nurse;
        this.units = 5;
        this.price = 1_000;
        this.duration = 20_000;
    }

    @Override
    public int getNameId(){
        return this.nameId;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getUnits(){
        return this.units;
    }

    @Override
    public boolean consume(){
        boolean allowed = units>0;
        if (allowed){
            units--;
            int physicalHealth = GameController.getInstance().getBeast().getPhysicalHealth();
            GameController.getInstance().getBeast().setPhysicalHealth(Math.min(100, physicalHealth+25));
        }
        return allowed;
    }

    @Override
    public long getDuration() {
        return this.duration;
    }
}
