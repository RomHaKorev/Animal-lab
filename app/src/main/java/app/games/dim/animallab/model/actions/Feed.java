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
    public int getUnits(){
        return this.units;
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
