package app.games.dim.animallab.tools;

import android.content.Context;

import java.util.Date;

import app.games.dim.animallab.R;

/**
 * Created by Igor on 09/02/2018.
 */

public class DurationFormater {

    private static long getDurationInMillis(Date begin, Date end){
        return end.getTime()-begin.getTime();
    }

    private static String formatDuration(Context context, Date begin, Date end){
        return formatDuration(context, getDurationInMillis(begin, end));
    }

    private static String formatDuration(Context context, long duration){
        long seconds = duration / 1000;
        if (seconds < 60){
            return Long.toString(seconds) + context.getString(R.string.second_short);
        }
        else if (seconds<3600){
            return Long.toString(seconds/60) + context.getString(R.string.minute_short) + " "
                    + Long.toString(seconds%60) + context.getString(R.string.second_short);
        }
        else if (seconds<86400){
            long hours = seconds/3600;
            long minutes = (seconds - hours*3600) / 60;
            return Long.toString(hours) + context.getString(R.string.hour_short) + " "
                    + Long.toString(minutes) + context.getString(R.string.minute_short) + " "
                    + Long.toString(seconds%60) + context.getString(R.string.second_short);
        }
        else {
            return "undefined";
        }
    }
}
