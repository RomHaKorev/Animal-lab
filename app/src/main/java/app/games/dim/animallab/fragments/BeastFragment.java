    /*
        This file is part of Animal Lab

        Animal Lab, Another Android Game
        Copyright (C) 2018 ERD IFT MHU

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
     */

    package app.games.dim.animallab.fragments;

    import android.animation.Animator;
    import android.animation.AnimatorListenerAdapter;
    import android.animation.AnimatorSet;
    import android.animation.ObjectAnimator;
    import android.content.Context;
    import android.graphics.Typeface;
    import android.os.Bundle;
    import android.os.Handler;
    import android.support.v4.app.Fragment;
    import android.text.Layout;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.BaseAdapter;
    import android.widget.FrameLayout;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;

    import app.games.dim.animallab.adapters.IndicatorsAdapter;
    import app.games.dim.animallab.R;
    import app.games.dim.animallab.formatters.MoneyFormatter;
    import app.games.dim.animallab.listeners.IBeastListener;
    import app.games.dim.animallab.listeners.OnSwipeTouchListener;
    import app.games.dim.animallab.model.Beast;
    import app.games.dim.animallab.model.GameController;
    import app.games.dim.animallab.model.Mutation;
    import app.games.dim.animallab.model.structures.BeastTemplate;

    /**
     * A simple {@link Fragment} subclass.
     */
    public class BeastFragment extends Fragment implements IBeastListener {

        private Typeface mFont;
        private TextView mMoneyText;
        private TextView mBeastName;
        private TextView mBeastAge;
        private ImageView mBeastGender;
        private ListView mIndicatorsView;

        private View mBeastView;

        private ImageView mRightEyelidView;
        private ImageView mLeftEyelidView;

        private Handler mHandler;
        private Runnable mRunnable;

        public BeastFragment() {
            // Required empty public constructor
            mBeastView = null;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.mFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Capture_it.ttf");
            this.mHandler = new Handler();
            this.mRunnable = new Runnable() {
                @Override
                public void run() {
                    Log.d(BeastFragment.class.getSimpleName(), "animate...");
                    animateEyes();
                    mHandler.postDelayed(this, Beast.EYELID_WINK_DURATION);
                }
            };
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView =  inflater.inflate(R.layout.fragment_beast, container, false);

            mBeastView = rootView.findViewById(R.id.beast_view);

            TextView moneyLbl = (TextView) rootView.findViewById(R.id.money_label);
            moneyLbl.setTypeface(mFont);
            mMoneyText = (TextView) rootView.findViewById(R.id.money_value);
            mMoneyText.setTypeface(mFont);

            mBeastName = (TextView) rootView.findViewById(R.id.beast_name);
            mBeastName.setTypeface(mFont);

            mBeastAge = (TextView) rootView.findViewById(R.id.beast_age);
            mBeastAge.setTypeface(mFont);

            mBeastGender = (ImageView) rootView.findViewById(R.id.beast_gender);

            setBeastIdentification();

            mIndicatorsView = (ListView) rootView.findViewById(R.id.indicators_list);
            mIndicatorsView.setAdapter(new IndicatorsAdapter(getContext(), GameController.getInstance().getBeast()));

            mRightEyelidView = (ImageView) rootView.findViewById(R.id.right_eyelid);
            mLeftEyelidView = (ImageView) rootView.findViewById(R.id.left_eyelid);

            View body_image = (View) rootView.findViewById(R.id.body);
        body_image.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
        public void onSwipeBottom() {
            if (this.accepted())
            {
                Beast beast = GameController.getInstance().getBeast();
                int stress = beast.getStress();
                if (stress - 1 > 0) {
                    beast.setStress(stress - 1);
                }
                else {
                    beast.setStress(0);
                }
                //s = "Reduce Stress " + beast.getStress();
                onGameChanged();
            }
         }
        });


            return rootView;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }

        @Override
        public void onResume(){
            super.onResume();
            GameController.getInstance().registerBeastListener(this);
            Log.v(getClass().getSimpleName(), "onResume. Registering beastListener");
            mHandler.postDelayed(mRunnable,100);
        }

        @Override
        public void onPause(){
            super.onPause();
            GameController.getInstance().unregister(this);
            mHandler.removeCallbacks(mRunnable);
            Log.v(getClass().getSimpleName(), "onPause. Unregistering beastListener");
        }

        @Override
        public void onDetach() {
            super.onDetach();
        }

        @Override
        public void onBeastChanged(Mutation mutationEvent){
            Log.v(getClass().getSimpleName(),"onBeastChanged");
            setBeastIdentification();
            ((BaseAdapter) mIndicatorsView.getAdapter()).notifyDataSetChanged();
            if (mutationEvent.bodyPart != BeastTemplate.EPart.NONE) {
                onMutation(mutationEvent);
            }
        }

        public void onGameChanged() {
            Log.v(getClass().getSimpleName(), "onGameChanged");
            setBeastIdentification();
            ((BaseAdapter) mIndicatorsView.getAdapter()).notifyDataSetChanged();
        }

        private void animateEyes(){
            this.mRightEyelidView.setPivotY(0.0f);
            this.mLeftEyelidView.setPivotY(0.0f);
            AnimatorSet leftAnimator = createAnimator(mLeftEyelidView);
            AnimatorSet rightAnimator = createAnimator(mRightEyelidView);

            leftAnimator.start();
            rightAnimator.start();

        }
        private AnimatorSet createAnimator(ImageView imageView){
            AnimatorSet animator = new AnimatorSet();

            ObjectAnimator eyelidMoveUp = ObjectAnimator.ofFloat(imageView, "scaleY",0.0f);
            eyelidMoveUp.setDuration(60);
            ObjectAnimator eyelidMoveDown = ObjectAnimator.ofFloat(imageView, "scaleY",1.0f);
            eyelidMoveDown.setDuration(60);

            animator.play(eyelidMoveDown).before(eyelidMoveUp);
            animator.setDuration(140);

            return animator;
        }

        private void onMutation(final Mutation mutation){
            if (mBeastView == null)
                return;

            final ImageView view = mBeastView.findViewById(mutation.new_part_id);
            final ImageView curr_img_view = mBeastView.findViewById(mutation.old_part_id);
            if (view == null || curr_img_view == null) {
                Log.e("beastfragment", "mutation: View is null");
                return;
            }

            ObjectAnimator add_part_animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
            add_part_animator.setDuration(1000);
            add_part_animator.addListener(
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            view.setVisibility(View.VISIBLE);
                        }
                    }
            );
            ObjectAnimator remove_part_animator = ObjectAnimator.ofFloat(curr_img_view, "alpha", 0);
            remove_part_animator.setDuration(1000);
            add_part_animator.addListener(
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationEnd(animation);
                            curr_img_view.setVisibility(View.INVISIBLE);
                        }
                    }
            );
            AnimatorSet animator = new AnimatorSet();
            animator.playSequentially(remove_part_animator, add_part_animator);
            animator.start();
        }

        private void setBeastIdentification(){
            // set beast name and age
            mBeastName.setText(GameController.getInstance().getBeast().getName());
            mBeastAge.setText("5 days");

            // set wallet content
            Double moneyValue = GameController.getInstance().getWallet().getAccount();
            mMoneyText.setText(MoneyFormatter.FORMATTER.format(moneyValue));

            // set beast gender
            switch (GameController.getInstance().getBeast().getGender()){
                case FEMALE:
                    mBeastGender.setImageResource(R.drawable.female_gender);
                    break;
                case MALE:
                    mBeastGender.setImageResource(R.drawable.male_gender);
                    break;
                case TRANS:
                    mBeastGender.setImageResource(R.drawable.trans_gender);
                    break;
            }
        }
    }
