package app.games.dim.animallab.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import app.games.dim.animallab.R;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Capture_it.ttf");
        TextView studioLabel = (TextView) findViewById(R.id.studio_label);
        studioLabel.setTypeface(font);

        ImageView imageView = (ImageView) findViewById(R.id.splash);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashscreenActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
