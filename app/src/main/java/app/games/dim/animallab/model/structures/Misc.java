package app.games.dim.animallab.model.structures;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.MISC;

/**
 * Created by Dim on 15/04/2018.
 */

public class Misc extends BeastTemplate {

    public EType id() {
        return MISC;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public void notifySelection(EPart id) {
        if (!possibilites.containsKey(id))
            return;

        int size = possibilites.get(id).size();
        Random rnd = new Random();
        int new_idx = rnd.nextInt(size);
        int i = 50; // To prevent long loop
        while((new_idx == current_images.get(id)) && (i >= 0)) {
            new_idx = rnd.nextInt(size);
            --i;
        }
        current_images.remove(id);
        current_images.put(id, new_idx);
    }

    public int image(EPart id) {
        if (current_images.containsKey(id)) {
            return possibilites.get(id).get(current_images.get(id));
        }
        return -1;
    }

    public Misc() {
        super();
        current_images = new HashMap<EPart, Integer>();
        possibilites = new HashMap<EPart, ArrayList<Integer> >();

        possibilites.put(EPart.MOUTH, new ArrayList<Integer>());
        possibilites.put(EPart.EYE_RIGHT, new ArrayList<Integer>());
        possibilites.put(EPart.EYE_LEFT, new ArrayList<Integer>());

        possibilites.get(EPart.MOUTH).add(R.id.part_misc_mouth_pig_rabbit_teeth);
        possibilites.get(EPart.MOUTH).add(R.id.part_rabbit_mouth);

        possibilites.get(EPart.EYE_RIGHT).add(R.id.part_misc_right_eye_drug);
        possibilites.get(EPart.EYE_RIGHT).add(R.id.part_misc_right_eye_drug_red);
        possibilites.get(EPart.EYE_RIGHT).add(R.id.part_misc_right_eye_hole);
        possibilites.get(EPart.EYE_RIGHT).add(R.id.part_misc_right_eye_snake);

        possibilites.get(EPart.EYE_LEFT).add(R.id.part_misc_left_eye_drug);
        possibilites.get(EPart.EYE_LEFT).add(R.id.part_misc_left_eye_drug_red);
        possibilites.get(EPart.EYE_LEFT).add(R.id.part_misc_left_eye_hole);
        possibilites.get(EPart.EYE_LEFT).add(R.id.part_misc_left_eye_snake);

        current_images.put(EPart.EYE_RIGHT, 0);
        current_images.put(EPart.MOUTH, 0);
        current_images.put(EPart.EYE_LEFT, 0);
    }
    private HashMap<EPart, ArrayList<Integer> > possibilites;
    private HashMap<EPart, Integer> current_images;
}
