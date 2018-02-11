package app.games.dim.animallab.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

import app.games.dim.animallab.adapters.IndicatorsAdapter;
import app.games.dim.animallab.R;
import app.games.dim.animallab.listeners.IBeastListener;
import app.games.dim.animallab.model.GameController;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeastFragment extends Fragment implements IBeastListener {

    private Typeface mFont;
    private TextView mMoneyText;

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
        mMoneyText.setText(Double.toString(GameController.getInstance().getWallet().getAccount()));

        ListView listView = (ListView) rootView.findViewById(R.id.indicators_list);
        listView.setAdapter(new IndicatorsAdapter(getContext(), GameController.getInstance().getBeast()));
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
    public void onGameChanged(){
        this.mMoneyText.setText(Double.toString(GameController.getInstance().getWallet().getAccount()));
    }
}
