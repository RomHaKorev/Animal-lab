package app.games.dim.animallab.model.structures;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.OCTOPUS;

public class Octopus extends BeastTemplate {

    public EType id() {
        return OCTOPUS;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
                case ARM_LEFT:
                    return R.id.part_octopus_left_arm;
                case EAR_LEFT:
                    return R.id.part_octopus_left_ear;
                case EYE_LEFT:
                    return R.id.part_octopus_left_eye;
                case LEG_LEFT:
                    return R.id.part_octopus_left_foot;
                case ARM_RIGHT:
                    return R.id.part_octopus_right_arm;
                case EAR_RIGHT:
                    return R.id.part_octopus_right_ear;
                case EYE_RIGHT:
                    return R.id.part_octopus_right_eye;
                case LEG_RIGHT:
                    return R.id.part_octopus_right_foot;
                case BODY:
                    return R.id.part_octopus_body;
                case HEAD:
                    return R.id.part_octopus_face;
                case MOUTH:
                    return R.id.part_octopus_mouth;
                default:
                    return -1;
        }
    }

    public Octopus() {
        super();
    }
}
