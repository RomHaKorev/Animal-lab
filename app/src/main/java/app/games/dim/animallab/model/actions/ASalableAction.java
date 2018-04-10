package app.games.dim.animallab.model.actions;

/**
 * Created by Igor on 06/04/2018.
 */

public abstract class ASalableAction extends AAction {

    public enum EType {
        SYRINGE,
        SURGERY,
        POTION,
        DNA,
        PSYCHO,
        WHIP
    }

    private EType type;

    protected ASalableAction(EType type){
        this.type = type;
    }

    public EType getType(){
        return this.type;
    }

    /** Return the risk that represents accepting the proposed challenge */
    public abstract double getRisk();

    /** Return the amount of money that the player can earn, by accepting the challenge */
    public abstract double getEarnableMoney();
}
