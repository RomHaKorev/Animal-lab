package app.games.dim.animallab.model;

/**
 * Created by Igor on 08/04/2018.
 */

public class Mutation {

    public enum EBodyPart {
        NONE,
        BODY,
        HEAD,
        LEFT_EAR,
        RIGHT_EAR,
        LEFT_EYELID,
        RIGHT_EYELID,
        LEFT_EYE,
        RIGHT_EYE,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG
    }

    public final EBodyPart bodyPart;

    public Mutation(EBodyPart part){
        this.bodyPart = part;
    }
}
