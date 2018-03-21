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

/**
 * Created by Igor on 10/02/2018.
 */

public abstract class AAction {

    /** Return the id of the string resource corresponding to the action name */
    public abstract int getNameId();

    /** Return the price of the action */
    public abstract double getPrice();

    /** Return the number of available units */
    public abstract int getUnits();

    /** Return <code>true</code> if the player has enough resource, and consume it */
    public abstract boolean consume();

    /** Return the duration of the action */
    public abstract long getDuration();
}
