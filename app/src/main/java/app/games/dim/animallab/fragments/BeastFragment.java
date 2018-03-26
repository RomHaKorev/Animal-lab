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

package app.games.dim.animallab.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import app.games.dim.animallab.activities.GameActivity;
import app.games.dim.animallab.adapters.IndicatorsAdapter;
import app.games.dim.animallab.R;
import app.games.dim.animallab.listeners.IBeastListener;
import app.games.dim.animallab.listeners.OnSwipeTouchListener;
import app.games.dim.animallab.model.Beast;
import app.games.dim.animallab.model.GameController;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeastFragment extends Fragment implements IBeastListener {

    private Typeface mFont;
    private TextView mMoneyText;
    private TextView mBeastName;
    private TextView mBeastAge;
    private ImageView mBeastGender;
    private ListView mIndicatorsView;

    public BeastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Capture_it.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_beast, container, false);

        TextView moneyLbl = (TextView) rootView.findViewById(R.id.money_label);
        moneyLbl.setTypeface(mFont);
        mMoneyText = (TextView) rootView.findViewById(R.id.money_value);
        mMoneyText.setTypeface(mFont);

        mBeastName = (TextView) rootView.findViewById(R.id.beast_name);
        mBeastName.setTypeface(mFont);

        mBeastAge = (TextView) rootView.findViewById(R.id.beast_age);
        mBeastAge.setTypeface(mFont);

        mBeastGender = (ImageView) rootView.findViewById(R.id.beast_gender);

        setBeastIdentification();

        mIndicatorsView = (ListView) rootView.findViewById(R.id.indicators_list);
        mIndicatorsView.setAdapter(new IndicatorsAdapter(getContext(), GameController.getInstance().getBeast()));

        ImageView body_image = (ImageView) rootView.findViewById(R.id.body);
        body_image.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeBottom() {
                //String s = "Reduce stress " + this.accepted();
                if (this.accepted())
                {
                    Beast beast = GameController.getInstance().getBeast();
                    int stress = beast.getStress();
                    if (stress - 1 > 0) {
                        beast.setStress(stress - 1);
                    }
                    else {
                        beast.setStress(0);
                    }
                    //s = "Reduce Stress " + beast.getStress();
                    onGameChanged();
                }
                //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume(){
        super.onResume();
        GameController.getInstance().registerBeastListener(this);
        Log.v(getClass().getSimpleName(), "onResume. Registering beastListener");
    }

    @Override
    public void onPause(){
        super.onPause();
        GameController.getInstance().unregister(this);
        Log.v(getClass().getSimpleName(), "onPause. Unregistering beastListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onGameChanged(){
        Log.v(getClass().getSimpleName(),"onGameChanged");
        setBeastIdentification();
        ((BaseAdapter) mIndicatorsView.getAdapter()).notifyDataSetChanged();

    }

    private void setBeastIdentification(){
        // set beast name and age
        mBeastName.setText(GameController.getInstance().getBeast().getName());
        mBeastAge.setText("5 days");

        // set wallet content
        mMoneyText.setText(Double.toString(GameController.getInstance().getWallet().getAccount())+" $");

        // set beast gender
        switch (GameController.getInstance().getBeast().getGender()){
            case FEMALE:
                mBeastGender.setImageResource(R.drawable.female_gender);
                break;
            case MALE:
                mBeastGender.setImageResource(R.drawable.male_gender);
                break;
            case TRANS:
                mBeastGender.setImageResource(R.drawable.trans_gender);
                break;
        }
    }
}
