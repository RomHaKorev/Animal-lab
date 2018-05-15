package app.games.dim.animallab.model.structures;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.CRAB;

public class Crab extends BeastTemplate {

    public EType id() { return CRAB; }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
            case ARM_LEFT:
                return R.id.part_crab_left_arm;
            default:
                return -1;
        }
    }

    public Crab() {
        super();
    }
}
