package app.games.dim.animallab.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.BaseAdapter;

/**
 * Created by Igor on 05/04/2018.
 */

public abstract class AMarketAdapter extends BaseAdapter {

    protected Context mContext;
    protected Typeface mFont;

    public AMarketAdapter(Context context){
        this.mContext = context;
        this.mFont = Typeface.createFromAsset(context.getAssets(), "fonts/Capture_it.ttf");
    }
}
