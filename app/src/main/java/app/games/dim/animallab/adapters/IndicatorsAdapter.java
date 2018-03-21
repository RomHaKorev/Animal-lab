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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.model.Beast;
import app.games.dim.animallab.animations.ProgressBarAnimation;

/**
 * Created by Igor on 09/02/2018.
 */

public class IndicatorsAdapter extends BaseAdapter {

    private static final int HEALTH_PHY = 0;
    private static final int HEALTH_MENTAL = 1;
    private static final int STRESS = 2;
    private static final int HUNGER = 3;

    private static final int NUMBER_OF_INDICATORS = 4;
    private Context mContext;
    private Typeface mFont;
    private Beast mBeast;

    private ProgressBar[] mItems;

    public IndicatorsAdapter(Context context, Beast beast){
        this.mContext = context;
        this.mFont = Typeface.createFromAsset(context.getAssets(), "fonts/Capture_it.ttf");
        this.mBeast = beast;
        this.mItems = new ProgressBar[NUMBER_OF_INDICATORS];
    }

    @Override
    public int getCount() {
        return NUMBER_OF_INDICATORS;
    }

    @Override
    public Object getItem(int i) {
        return this.mItems[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    private void animateProgressBar(ProgressBar bar, int newValue, boolean reversed)
    {
        final int ORANGE = -16791936;
        int color;
        if (reversed)
        {
            if (newValue < 60)
                color = Color.GREEN;
            else if (newValue < 80)
                color = ORANGE;
            else
                color = Color.RED;
        }
        else
        {
            if (newValue < 20)
                color = Color.RED;
            else if (newValue < 40)
                color = ORANGE;
            else
                color = Color.GREEN;
        }
        bar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        int oldValue = bar.getProgress();
        ProgressBarAnimation anim = new ProgressBarAnimation(bar, oldValue, newValue);
        anim.setDuration(3000);
        bar.startAnimation(anim);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem_indicator, null);
        }
        ImageView img = (ImageView) view.findViewById(R.id.indicator_img);
        TextView label = (TextView) view.findViewById(R.id.indicator_label);
        label.setTypeface(mFont);
        ProgressBar bar = (ProgressBar) view.findViewById(R.id.indicator_gauge);
        int newValue = -1;
        switch (position) {
            case HEALTH_PHY:
                img.setImageResource(R.drawable.if_3_hospital_2774749);
                label.setText(mContext.getString(R.string.physical_health));
                mItems[position] = bar;
                newValue = mBeast.getPhysicalHealth();
                animateProgressBar(bar, newValue,false);
                break;
            case HEALTH_MENTAL:
                img.setImageResource(R.drawable.if_brain_1626489);
                label.setText(mContext.getString(R.string.mental_health));
                mItems[position] = bar;
                newValue = mBeast.getMentalHealth();
                animateProgressBar(bar, newValue, false);
                break;
            case STRESS:
                img.setImageResource(R.drawable.if_smiley__6_2291008);
                label.setText(mContext.getString(R.string.stress));
                mItems[position] = bar;
                newValue = mBeast.getStress();
                animateProgressBar(bar, newValue, true);
                break;
            case HUNGER:
                img.setImageResource(R.drawable.if_burger_653249);
                label.setText(mContext.getString(R.string.hunger));
                mItems[position] = bar;
                newValue = mBeast.getHunger();
                animateProgressBar(bar, newValue, true);
                break;
        }
        return view;
    }

    @Override
    public void notifyDataSetChanged(){
        Log.v(getClass().getSimpleName(), "notifyDataSetChanged");
        ProgressBar bar;
        for (int i = 0; i < getCount(); i++) {
            bar = ((ProgressBar) getItem(i));
            if(bar!=null) {
                int oldValue = ((ProgressBar) getItem(i)).getProgress();
                int newValue = 0;
                switch (i) {
                    case 0:
                        newValue = mBeast.getPhysicalHealth();
                        break;
                    case 1:
                        newValue = mBeast.getMentalHealth();
                        break;
                    case 2:
                        newValue = mBeast.getStress();
                        break;
                    case 3:
                        newValue = mBeast.getHunger();
                        break;
                }
                if (oldValue != 0 && oldValue != newValue) {
                    Log.v(getClass().getSimpleName(), "Change value from " + oldValue + " to " + newValue + " for " + bar);
                }
            }
        }
        super.notifyDataSetChanged();
    }
}
