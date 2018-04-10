package app.games.dim.animallab.formatters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Igor on 08/04/2018.
 */

public class MoneyFormatter extends DecimalFormat {

    public static DecimalFormat FORMATTER;
    static {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(' ');
        FORMATTER = new DecimalFormat("###,### $", dfs);
    }

}
