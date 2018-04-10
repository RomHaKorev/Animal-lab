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
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.adapters.AMarketAdapter;
import app.games.dim.animallab.adapters.MerchandableActionsAdapter;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.model.actions.ASalableAction;

/**
 * Created by Igor on 19/02/2018.
 */

public class MarketFragment extends Fragment {

    private IActionClickListener mListener;
    private Typeface mFont;
    private ListView mListView;

    private Button mInjectionButton;
    private Button mSurgeryButton;
    private Button mPotionButton;
    private Button mGeneticButton;
    private Button mPsychoButton;
    private Button mWhipButton;

    private MarketNestedFragment mActionsFragment;

    public MarketFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);
        this.mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Capture_it.ttf");

        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setTypeface(mFont);

        initializeButtons(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IActionClickListener) {
            mListener = (IActionClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void initializeButtons(final View rootView){
        this.mInjectionButton = (Button) rootView.findViewById(R.id.injection_button);
        mapAction(this.mInjectionButton, rootView.getContext(), ASalableAction.EType.SYRINGE);

        this.mSurgeryButton = (Button) rootView.findViewById(R.id.surgery_button);
        mapAction(this.mSurgeryButton, rootView.getContext(), ASalableAction.EType.SURGERY);

        this.mPotionButton = (Button) rootView.findViewById(R.id.potion_button);
        mapAction(this.mPotionButton, rootView.getContext(), ASalableAction.EType.POTION);

        this.mGeneticButton = (Button) rootView.findViewById(R.id.genetic_button);
        mapAction(this.mGeneticButton, rootView.getContext(), ASalableAction.EType.DNA);

        this.mPsychoButton = (Button) rootView.findViewById(R.id.psycho_button);
        this.mWhipButton = (Button) rootView.findViewById(R.id.whip_button);
    }

    private void mapAction(Button button, final Context context, final ASalableAction.EType type){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AMarketAdapter adapter = new MerchandableActionsAdapter(context, type);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                if (mActionsFragment!=null) {
                    transaction.remove(mActionsFragment);
                }
                mActionsFragment = new MarketNestedFragment();
                mActionsFragment.setAdapter(adapter);
                transaction.add(R.id.list_container, mActionsFragment).commit();
            }
        });
    }

}
