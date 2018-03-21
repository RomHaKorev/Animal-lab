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

package app.games.dim.animallab.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.model.Beast;
import app.games.dim.animallab.model.GameController;
import app.games.dim.animallab.model.actions.AAction;

/**
 * Created by Igor on 09/02/2018.
 */

public class ActionsAdapter extends BaseAdapter {

    private Context mContext;
    private Typeface mFont;

    public ActionsAdapter(Context context){
        this.mContext = context;
        this.mFont = Typeface.createFromAsset(context.getAssets(), "fonts/Capture_it.ttf");
    }

    @Override
    public int getCount() {
        return GameController.getInstance().getActions().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem_action, null);
        }
        TextView actionName = (TextView) view.findViewById(R.id.action_label);
        TextView actionUnits = (TextView) view.findViewById(R.id.action_units);


        final AAction action = GameController.getInstance().getActions().get(position);
        actionName.setTypeface(mFont);
        actionName.setText(action.getNameId());
        actionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IActionClickListener)view.getContext()).onActionSelected(action);
            }
        });

        actionUnits.setTypeface(mFont);
        actionUnits.setText(Integer.toString(action.getUnits())+" "+mContext.getString(R.string.units));

        return view;
    }
}
