package app.games.dim.animallab.model;

import app.games.dim.animallab.model.structures.BeastTemplate;

/**
 * Created by Igor on 08/04/2018.
 */

public class Mutation {
    public final BeastTemplate.EPart bodyPart;
    public final Integer new_part_id;
    public final Integer old_part_id;
    public Mutation(BeastTemplate.EPart part, Integer part_id, Integer previous_part_id){
        this.bodyPart = part;
        this.new_part_id = part_id;
        this.old_part_id = previous_part_id;
    }
}
