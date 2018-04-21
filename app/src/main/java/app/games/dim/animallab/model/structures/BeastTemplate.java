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

        private static final List<EPart> _VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int _SIZE = _VALUES.size() - 1;
        private static final Random _RANDOM = new Random();

        public static EPart random() {
            return _VALUES.get(_RANDOM.nextInt(_SIZE));
        }

        public static List<EPart> shuffle() {
            List<EPart> r = Arrays.asList(values());
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
        PIGV2;

        private static final List<EType> _VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int _SIZE = _VALUES.size();
        private static final Random _RANDOM = new Random();

        public static EType random() {
            return _VALUES.get(_RANDOM.nextInt(_SIZE));
        }

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
        for(BeastTemplate.EType foobar: BeastTemplate.strcts.keySet()) {
            Log.v("ERD", "" + foobar);
        }
        return strcts.get(id);
    }
}
