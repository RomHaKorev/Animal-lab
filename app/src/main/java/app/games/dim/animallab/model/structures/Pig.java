package app.games.dim.animallab.model.structures;

import android.util.Log;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.FLESH;
import static app.games.dim.animallab.model.structures.BeastTemplate.EType.PIG;

/**
 * Created by Dim on 15/04/2018.
 */

public class Pig extends BeastTemplate {
    public EType id() {
        return PIG;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
            case ARM_LEFT:
                return R.id.part_pig_left_arm;
            case EAR_LEFT:
                return R.id.part_pig_left_ear;
            case EYE_LEFT:
                return R.id.part_pig_left_eye;
            case LEG_LEFT:
                return R.id.part_pig_left_foot;
            case ARM_RIGHT:
                return R.id.part_pig_right_arm;
            case EAR_RIGHT:
                return R.id.part_pig_right_ear;
            case EYE_RIGHT:
                return R.id.part_pig_right_eye;
            case LEG_RIGHT:
                return R.id.part_pig_right_foot;
            case BODY:
                return R.id.part_pig_body;
            case HEAD:
                return R.id.part_pig_head;
            case MOUTH:
                return R.id.part_pig_mouth;
            default:
                return -1;
        }
    }

    public Pig() {
        super();
    }
}
