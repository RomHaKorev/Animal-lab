package app.games.dim.animallab.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
/**
 * Created by Igor on 28/03/2018.
 */

public class BlinkingAnimation extends Animation {

    private ImageView imageView;
    private int original, next;

    public BlinkingAnimation(ImageView view, int originalResId, int newResId){
        this.imageView = view;
        this.original = originalResId;
        this.next = newResId;
    }

    @Override
    public void applyTransformation(float interpolatedTime, Transformation transformation){
        super.applyTransformation(interpolatedTime,transformation);
//        float value = from + (to - from) * interpolatedTime;
//        progressBar.setProgress((int) value);
    }
}
