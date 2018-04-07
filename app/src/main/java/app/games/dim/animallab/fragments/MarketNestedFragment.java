package app.games.dim.animallab.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.adapters.AMarketAdapter;
import app.games.dim.animallab.adapters.ActionsAdapter;

/**
 * Created by Igor on 05/04/2018.
 */

public class MarketNestedFragment extends Fragment {


    private ListView mListView;
    private BaseAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarketNestedFragment() {
    }

    public void setAdapter(AMarketAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_market_list, container, false);

        this.mListView = (ListView) rootView.findViewById(R.id.list);
        this.mListView.setAdapter(this.mAdapter);

        return rootView;
    }

}