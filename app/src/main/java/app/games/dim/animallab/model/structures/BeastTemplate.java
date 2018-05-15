package app.games.dim.animallab.model.structures;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Dim on 14/04/2018.
 */

public abstract class BeastTemplate {
    public enum EPart {
        LEG_LEFT,
        LEG_RIGHT,
        ARM_LEFT,
        ARM_RIGHT,
        EYE_LEFT,
        EYE_RIGHT,
        EAR_LEFT,
        EAR_RIGHT,
        BODY,
        HEAD,
        MOUTH,
        NONE;

        public static List<EPart> shuffle() {
            List<EPart> r = new ArrayList<EPart>(Arrays.asList(values()));
            r.remove(r.size() - 1);
            Collections.shuffle(r);
            return r;
        }
    }

    public enum EType {
        PIG,
        FLESH,
        INSECT,
        GOAT,
        CRAB,
        PIGV2,
        OCTOPUS,
        MISC;

        public static List<EType> shuffle() {
            List<EType> r = Arrays.asList(values());
            Collections.shuffle(r);
            return r;
        }
    }

    public abstract int layout();
    public abstract int image(EPart id);
    public abstract EType id();


    public BeastTemplate() {}

    private static HashMap<EType, BeastTemplate> strcts = new HashMap<EType, BeastTemplate>();
    public static void register(BeastTemplate strct) {
        strcts.put(strct.id(), strct);
    }

    public static BeastTemplate get(EType id) {
        return strcts.get(id);
    }
}
