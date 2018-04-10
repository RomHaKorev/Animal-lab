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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.adapters.ActionsAdapter;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.listeners.IActionsListener;
import app.games.dim.animallab.model.GameController;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IActionClickListener}
 * interface.
 */
public class ActionsFragment extends Fragment implements IActionsListener {

    private Context mContext;
    private ListView mListView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_action, container, false);

        mContext = rootView.getContext();
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Capture_it.ttf");

        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setTypeface(font);

        this.mListView = (ListView) rootView.findViewById(R.id.list);
        this.mListView.setAdapter(new ActionsAdapter(mContext));

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        GameController.getInstance().registerActionsListener(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        GameController.getInstance().unregister(this);
    }

    @Override
    public void onActionChanged(){
        this.mListView.setAdapter(new ActionsAdapter(mContext));
    }
}
