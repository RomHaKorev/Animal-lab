package app.games.dim.animallab.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import app.games.dim.animallab.R;
import app.games.dim.animallab.fragments.SectionsPagerAdapter;
import app.games.dim.animallab.listeners.IActionClickListener;
import app.games.dim.animallab.model.GameController;
import app.games.dim.animallab.model.actions.AAction;

public class GameActivity extends AppCompatActivity implements IActionClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // No action bar, so no item clicks to handle here.

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActionSelected(AAction action) {
        Log.v(getClass().getSimpleName(), "action selected "+action.getClass().getSimpleName());
        mViewPager.setCurrentItem(1, true);
        if (GameController.getInstance().execute(action)) {
            String msg = getString(action.getNameId()) + " the animal";
            Snackbar.make(this.mViewPager, msg, Snackbar.LENGTH_SHORT).show();
        }
        else {
            String msg = getString(R.string.action_refused);
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
    }
}
