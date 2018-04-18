package app.games.dim.animallab.model;

import app.games.dim.animallab.model.structures.BeastTemplate;

/**
 * Created by Igor on 08/04/2018.
 */

public class Mutation {
    public final BeastTemplate.EPart bodyPart;
    public final Integer new_image;
    public Mutation(BeastTemplate.EPart part, Integer img_id){
        this.bodyPart = part;
        this.new_image = img_id;
    }
}
