package app.games.dim.animallab.model.actions;

import app.games.dim.animallab.R;
import app.games.dim.animallab.model.GameController;

/**
 * Created by Igor on 10/02/2018.
 */

public class Feed extends AAction {

    private int nameId;

    private int units;
    private double price;
    private long duration;


    public Feed(){
        this.nameId = R.string.feed;
        this.units = 20;
        this.price = 100;
        this.duration = 5_000;
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
    public boolean consume(){
        boolean allowed = units>0;
        if (allowed){
            units--;
            int hunger = GameController.getInstance().getBeast().getHunger();
            GameController.getInstance().getBeast().setHunger(Math.max(0, hunger-50));
        }
        return allowed;
    }

    @Override
    public long getDuration() {
        return this.duration;
    }
}
