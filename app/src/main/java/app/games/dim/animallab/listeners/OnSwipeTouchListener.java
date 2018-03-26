package app.games.dim.animallab.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.Date;

public class OnSwipeTouchListener implements OnTouchListener {

    private final GestureDetector gestureDetector;
    private GestureListener gestureListener;

    public OnSwipeTouchListener (Context ctx){
        gestureListener = new GestureListener();
        gestureDetector = new GestureDetector(ctx, gestureListener);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public boolean accepted() {
        return (GestureListener.SWIPE_MINIMUM_COUNT <= gestureListener.count()
                && gestureListener.count() <= GestureListener.SWIPE_MAXIMUM_COUNT);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private static final int SWIPE_MINIMUM_COUNT = 5;
        private static final int SWIPE_MAXIMUM_COUNT = 10;
        private static final int SWIPE_MINIMUM_DURATION = 1_500;
        private static final int SWIPE_INTERVAL_DURATION = 10_000;
        private int mCount = 0;

        private Date mDate;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        public int count() { return mCount; }

        private void manageSwipeBottom()  {
            Date now = new Date();
            if (mDate != null && mDate.before(now))
            {
                mCount = 0;
            }
            mDate = new Date();
            mDate.setTime(mDate.getTime() + SWIPE_MINIMUM_DURATION);
            ++mCount;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        manageSwipeBottom();
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
    }

    public void onSwipeLeft() {
    }

    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }
}