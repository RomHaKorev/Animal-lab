package app.games.dim.animallab.model.structures;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.FLESH;

public class Flesh extends BeastTemplate {

    public EType id() {
        return FLESH;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
            case ARM_RIGHT:
                return R.id.part_flesh_right_arm;
            default:
                return -1;
        }
    }

    public Flesh() {
        super();
    }
}
