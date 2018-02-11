package app.games.dim.animallab.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.games.dim.animallab.model.actions.AAction;
import app.games.dim.animallab.model.actions.Feed;

/**
 * Created by Igor on 09/02/2018.
 */

public class GameController {

    /** The wallet is unique for a player and can not be change (so final) */
    private final Wallet mWallet;
    private Beast mBeast;

    private Date mDate;

    private List<AAction> availableActions;

    private GameController(){
        this.mWallet = new Wallet();
        this.mBeast = new Beast();
        this.mDate = new Date();
        this.availableActions = new ArrayList<>();
        this.availableActions.add(new Feed());
    }

    public Beast getBeast (){
        return this.mBeast;
    }

    public List<AAction> getActions() {
        return this.availableActions;
    }

    public boolean execute(AAction action){
        Date currentDate = new Date();
        if (currentDate.before(this.mDate)){
            return false;
        }
        boolean consumed = action.consume();
        if (consumed){
            setNextAvailability(action.getDuration());
        }
        return consumed;
    }

    private void setNextAvailability(long duration) {
        Date d = new Date();
        mDate.setTime(d.getTime()+duration);
    }

    public Wallet getWallet() {
        return this.mWallet;
    }

    public static GameController getInstance(){
        return GameControllerHolder.SINGLETON;
    }

    private static class GameControllerHolder {
        private static GameController SINGLETON = new GameController();
    }
}
