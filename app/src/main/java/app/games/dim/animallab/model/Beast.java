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

import app.games.dim.animallab.model.structures.BeastStructure;
import app.games.dim.animallab.model.structures.BeastTemplate;

/**
 * Created by Igor on 09/02/2018.
 */

public class Beast {

    public static final int EYELID_WINK_DURATION = 5_000;
    private String name;
    private EGender gender;
    private int physicalHealth;
    private int mentalHealth;
    private int stress;
    private int hunger;

    private BeastStructure structure;

    public Beast(){
        this.name = "SpecimenXA392";
        this.gender = EGender.FEMALE;
        this.mentalHealth = 70;
        this.physicalHealth = 75;
        this.stress = 50;
        this.hunger = 60;
        this.structure = new BeastStructure();
    }

    public String getName () {
        return this.name;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
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

    public Mutation mutate() {
        return this.structure.mutate();
    }
}
