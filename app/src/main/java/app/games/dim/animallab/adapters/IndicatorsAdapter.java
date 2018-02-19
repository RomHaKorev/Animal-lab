package app.games.dim.animallab.adapters;

import android.app.Activity;
import android.content.Context;
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

/**
 * Created by Igor on 09/02/2018.
 */

public class IndicatorsAdapter extends BaseAdapter {

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
        switch (position) {
            case 0:
                img.setImageResource(R.drawable.if_3_hospital_2774749);
                label.setText(mContext.getString(R.string.physical_health));
                mItems[0] = bar;
                bar.setProgress(mBeast.getPhysicalHealth());
                bar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                break;
            case 1:
                img.setImageResource(R.drawable.if_brain_1626489);
                label.setText(mContext.getString(R.string.mental_health));
                mItems[1] = bar;
                bar.setProgress(mBeast.getMentalHealth());
                bar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                break;
            case 2:
                img.setImageResource(R.drawable.if_smiley__6_2291008);
                label.setText(mContext.getString(R.string.stress));
                mItems[2] = bar;
                bar.setProgress(mBeast.getStress());
                break;
            case 3:
                img.setImageResource(R.drawable.if_burger_653249);
                label.setText(mContext.getString(R.string.hunger));
                mItems[3] = bar;
                bar.setProgress(mBeast.getHunger());
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
                    Log.v(getClass().getSimpleName(), "Change value from " + oldValue + " to " + newValue);
                }
            }
        }
        super.notifyDataSetChanged();
    }
}
