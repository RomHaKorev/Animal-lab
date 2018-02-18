package app.games.dim.animallab.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/**
 * Created by Igor on 18/02/2018.
 */

public class ProgressBarAnimation extends Animation {

    private ProgressBar progressBar;
    private float from, to;

    public ProgressBarAnimation(ProgressBar bar, float from, float to){
        this.progressBar = bar;
        this.from = from;
        this.to = to;
    }

    @Override
    public void applyTransformation(float interpolatedTime, Transformation transformation){
        super.applyTransformation(interpolatedTime,transformation);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
    }
}
