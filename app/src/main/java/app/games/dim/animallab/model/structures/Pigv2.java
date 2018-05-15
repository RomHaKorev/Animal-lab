package app.games.dim.animallab.model.structures;

import android.util.Log;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.PIGV2;

/**
 * Created by Dim on 15/04/2018.
 */

public class Pigv2 extends BeastTemplate {
    public EType id() {
        return PIGV2;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        /*switch(id) {
            case ARM_LEFT:
                return R.id.part_pigv2_left_arm;
            case EAR_LEFT:
                return R.id.part_pigv2_left_ear;
            case EYE_LEFT:
                return R.id.part_pigv2_left_eye;
            case LEG_LEFT:
                return R.id.part_pigv2_left_foot;
            case ARM_RIGHT:
                return R.id.part_pigv2_right_arm;
            case EAR_RIGHT:
                return R.id.part_pigv2_right_ear;
            case EYE_RIGHT:
                return R.id.part_pigv2_right_eye;
            case LEG_RIGHT:
                return R.id.part_pigv2_right_foot;
            case BODY:
                return R.id.part_pigv2_body;
            case HEAD:
                return R.id.part_pigv2_head;
            case MOUTH:
                return R.id.part_pigv2_mouth;
            default:
                return -1;
        }*/
    return -1;
    }

    public Pigv2() {
        super();
    }
}
