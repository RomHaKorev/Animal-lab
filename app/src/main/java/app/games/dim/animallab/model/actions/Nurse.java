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
