package app.games.dim.animallab.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import app.games.dim.animallab.R;
import app.games.dim.animallab.adapters.ActionsAdapter;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.model.GameController;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IActionClickListener}
 * interface.
 */
public class ActionsFragment extends Fragment {

    private IActionClickListener mListener;

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

        Context context = rootView.getContext();
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Capture_it.ttf");

        TextView title = (TextView) rootView.findViewById(R.id.title);
        title.setTypeface(font);

        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(new ActionsAdapter(context));

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IActionClickListener) {
            mListener = (IActionClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IActionClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
