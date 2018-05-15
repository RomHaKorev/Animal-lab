package app.games.dim.animallab.model.structures;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.INSECT;

public class Insect extends BeastTemplate {

    public EType id() {
        return INSECT;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
            case LEG_LEFT:
                return R.id.part_insect_left_foot;
            case MOUTH:
                return R.id.part_insect_mouth;
            default:
                return -1;
        }
    }

    public Insect() {
        super();
    }
}
