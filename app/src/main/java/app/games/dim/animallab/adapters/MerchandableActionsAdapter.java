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
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import app.games.dim.animallab.R;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.model.GameController;
import app.games.dim.animallab.model.actions.AAction;
import app.games.dim.animallab.model.actions.ASalableAction;

/**
 * Created by Igor on 07/04/2018.
 */

public class MerchandableActionsAdapter extends AMarketAdapter {

    private static final int HEADER_INDEX = 0;

    private ASalableAction.EType mType;
    private final DecimalFormat mFormat;

    public MerchandableActionsAdapter(Context context, ASalableAction.EType type){
        super(context);
        this.mType = type;

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(' ');
        this.mFormat = new DecimalFormat("###,### $");

    }

    @Override
    public int getCount() {
        // 1 is added to put header
        return GameController.getInstance().getNumberOfChallengingActions(mType)+1;
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
        // Retrieve TextView from the XML layout
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem_market, null);
        }
        TextView actionName = (TextView) view.findViewById(R.id.action_label);
        TextView actionRisk = (TextView) view.findViewById(R.id.risk_label);
        TextView actionEarn = (TextView) view.findViewById(R.id.earns_label);

        // Apply font on TextView
        actionName.setTypeface(mFont);
        actionRisk.setTypeface(mFont);
        actionEarn.setTypeface(mFont);

        // Fill TextView with their data
        if (position == HEADER_INDEX){
            actionName.setText("");
            actionRisk.setText("Risk");
            actionEarn.setText("Gain");
        }
        else {
            List<ASalableAction> actions = GameController.getInstance().getChallengingActions(mType);
            ASalableAction action = actions.get(position-1);
            actionName.setText(mContext.getString(action.getNameId()));
            actionEarn.setText(this.mFormat.format(action.getEarnableMoney()));
            actionRisk.setText(action.getRisk()+"%");
        }

        return view;
    }
}
