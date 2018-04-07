package app.games.dim.animallab.model.actions;

/**
 * Created by Igor on 07/04/2018.
 */

public class DnaHandling extends ASalableAction {

    private final int mKey;
    private final int mRisk;
    private final int mEarns;

    public DnaHandling(int key, int coefficient){
        super(EType.DNA);
        this.mKey = key;
        this.mRisk = (coefficient/5) * 5;
        this.mEarns = coefficient * 1000;
    }

    @Override
    public double getRisk() {
        return this.mRisk;
    }

    @Override
    public int getNameId() {
        return this.mKey;
    }

    @Override
    public double getEarnableMoney() {
        return this.mEarns;
    }

    @Override
    public int getUnits() {
        return 0;
    }

    @Override
    public boolean consume() {
        return false;
    }

    @Override
    public long getDuration() {
        return 0;
    }
}
