package app.games.dim.animallab.model.actions;

/**
 * Created by Igor on 10/02/2018.
 */

public abstract class AAction {

    /** Return the id of the string resource corresponding to the action name */
    public abstract int getNameId();

    /** Return the price of the action */
    public abstract double getPrice();

    /** Return <code>true</code> if the player has enough resource, and consume it */
    public abstract boolean consume();

    /** Return the duration of the action */
    public abstract long getDuration();
}
