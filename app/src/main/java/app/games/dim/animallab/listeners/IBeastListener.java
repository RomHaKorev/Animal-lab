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

package app.games.dim.animallab.listeners;

import app.games.dim.animallab.model.Mutation;

/**
 * Created by Igor on 11/02/2018.
 */

public interface IBeastListener {

    void onBeastChanged(Mutation mutationEvent);
}
