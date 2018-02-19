package app.games.dim.animallab.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import app.games.dim.animallab.R;

/**
 * Created by Igor on 19/02/2018.
 */

public class MarketFragment extends Fragment {

    private Typeface mFont;
    private ListView mListView;

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

        this.mListView = (ListView) rootView.findViewById(R.id.list);

        return rootView;
    }

}
