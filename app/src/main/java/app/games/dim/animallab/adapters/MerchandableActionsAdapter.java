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
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import app.games.dim.animallab.R;
import app.games.dim.animallab.activities.GameActivity;
import app.games.dim.animallab.formatters.MoneyFormatter;
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

    public MerchandableActionsAdapter(Context context, ASalableAction.EType type){
        super(context);
        this.mType = type;

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
            actionRisk.setText("Mutation Risk");
            actionRisk.setText("Injuries Risk");
            actionRisk.setText("Duration");
            actionEarn.setText("Gain");
        }
        else {
            List<ASalableAction> actions = GameController.getInstance().getChallengingActions(mType);
            final ASalableAction action = actions.get(position-1);
            actionName.setText(mContext.getString(action.getNameId()));
            actionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopup(mContext, action);
                }
            });
            actionEarn.setText(MoneyFormatter.FORMATTER.format(action.getEarnableMoney()));
            actionRisk.setText(action.getRisk()+"%");
        }

        return view;
    }

    // The method that displays the popup.
    private void showPopup(final Context context, final ASalableAction action) {
        //int popupWidth = 610;
        //int popupHeight = 320;

        // Inflate the popup_market_action.xml
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_market_action, null);

        //TextView title = layout.findViewById()

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        //popup.setWidth(popupWidth);
        //popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Clear the default translucent background
        //popup.setBackgroundDrawable(new BitmapDrawable());

        //popup.showAtLocation(layout, Gravity.CENTER, 0, 0);
        popup.showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        // Get references to OK/Cancel buttons, and map actions on buttons
        Button cancel = (Button) layout.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        Button ok = (Button) layout.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
                GameController.getInstance().testOnBeast(action);
                ((IActionClickListener)view.getContext()).onActionSelected(action);
            }
        });
        // Set dedicated font on all text areas (TextView, Buttons...)
        TextView titleView = (TextView) layout.findViewById(R.id.title);
        titleView.setTypeface(mFont);
        TextView message = (TextView) layout.findViewById(R.id.message);
        message.setTypeface(mFont);
        cancel.setTypeface(mFont);
        ok.setTypeface(mFont);
    }
}
