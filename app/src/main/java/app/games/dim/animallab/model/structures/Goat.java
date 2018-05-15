package app.games.dim.animallab.model.structures;

import app.games.dim.animallab.R;

import static app.games.dim.animallab.model.structures.BeastTemplate.EType.GOAT;

public class Goat extends BeastTemplate {

    public EType id() {
        return GOAT;
    }
    public int layout() {
        return R.layout.layout_beast;
    }

    public int image(EPart id) {
        switch(id) {
            case LEG_RIGHT:
                return R.id.part_goat_right_foot;
            default:
                return -1;
        }
    }

    public Goat() {
        super();
    }
}
