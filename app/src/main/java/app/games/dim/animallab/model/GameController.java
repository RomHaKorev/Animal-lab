/*
    This file is part of Animal Lab

    Animal Lab, Another Android Game
    Copyright (C) 2018 ERD IFT MHU

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package app.games.dim.animallab.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.games.dim.animallab.R;
import app.games.dim.animallab.listeners.IActionsListener;
import app.games.dim.animallab.listeners.IBeastListener;
import app.games.dim.animallab.model.actions.AAction;
import app.games.dim.animallab.model.actions.ACostingAction;
import app.games.dim.animallab.model.actions.ASalableAction;
import app.games.dim.animallab.model.actions.DnaHandling;
import app.games.dim.animallab.model.actions.Drink;
import app.games.dim.animallab.model.actions.Feed;
import app.games.dim.animallab.model.actions.Inoculation;
import app.games.dim.animallab.model.actions.Nurse;

/**
 * Created by Igor on 09/02/2018.
 */

public class GameController {

    /** The wallet is unique for a player and can not be change (so final) */
    private final Wallet mWallet;
    private Beast mBeast;

    private Date mDate;

    private List<ACostingAction> availableActions;
    private List<ASalableAction> challenges;
    private List<IBeastListener> mBeastListeners;
    private List<IActionsListener> mActionsListeners;

    private GameController(){
        this.mWallet = new Wallet();
        this.mBeast = new Beast();
        this.mDate = new Date();
        this.availableActions = new ArrayList<>();
        this.challenges = new ArrayList<>();
        this.mBeastListeners = new ArrayList<>();
        this.mActionsListeners = new ArrayList<>();
        this.availableActions.add(new Feed());
        this.availableActions.add(new Nurse());

        this.addSalableActions();
     }

    private void addSalableActions(){
        // Syringe
        this.challenges.add(new Inoculation(R.string.inoculate_sodium, 31));
        this.challenges.add(new Inoculation(R.string.inoculate_keratin, 45));

        // Potion
        this.challenges.add(new Drink(R.string.drink_energy_drink,15));
        this.challenges.add(new Drink(R.string.drink_poison,75));

        // DNA
        this.challenges.add(new DnaHandling(R.string.inject_crab_dna, 45));
        this.challenges.add(new DnaHandling(R.string.modify_allele, 55));
        this.challenges.add(new DnaHandling(R.string.modify_chromosome, 75));
        this.challenges.add(new DnaHandling(R.string.modify_genome, 95));

        // Psycho

        // Violence (whip)
    }

    public Beast getBeast (){
        return this.mBeast;
    }

    public List<ACostingAction> getActionsToPay() {
        return this.availableActions;
    }

    public List<ASalableAction> getChallengingActions(ASalableAction.EType type){
        List<ASalableAction> gauntlets = new ArrayList<>();

        for(ASalableAction act : this.challenges){
            if (act.getType() == type){
                gauntlets.add(act);
            }
        }
        return gauntlets;
    }

    /** Return the number of stored actions, according to the required type.
     * @param type the type of salable action
     * @return the number of concerned actions
     */
    public int getNumberOfChallengingActions(ASalableAction.EType type) {
        int count = 0;
        for (ASalableAction act : this.challenges){
            if (act.getType()==type){
                count++;
            }
        }
        return count;
    }

    public boolean execute(AAction action){
        Date currentDate = new Date();
        if (currentDate.before(this.mDate)){
            return false;
        }
        boolean consumed = action.consume();
        if (consumed){
            setNextAvailability(action.getDuration());
            for(IBeastListener listener : mBeastListeners){
                listener.onGameChanged();
            }
            for(IActionsListener listener : mActionsListeners){
                listener.onActionChanged();
            }
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


    public void registerBeastListener(IBeastListener listener){
        this.mBeastListeners.add(listener);
    }

    public void unregister(IBeastListener listener){
        for(IBeastListener bl : mBeastListeners){
            if (bl.equals(listener)){
                mBeastListeners.remove(listener);
            }
        }
    }

    public void registerActionsListener(IActionsListener listener){
        this.mActionsListeners.add(listener);
    }

    public void unregister(IActionsListener listener){
        for(IActionsListener al : mActionsListeners) {
            if (al.equals(listener)) {
                mActionsListeners.remove(listener);
            }
        }
    }

    public static GameController getInstance(){
        return GameControllerHolder.SINGLETON;
    }

    private static class GameControllerHolder {
        private static GameController SINGLETON = new GameController();
    }
}
