package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView groupName, presents;
    LinearLayout up_layout, down_layout;
    Animation left_to_wright, right_to_left, up_to_down, down_to_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Typeface typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Saleem_Quran.ttf");

        groupName = (TextView) findViewById(R.id.groupName);
        groupName.setTypeface(typeface);
        presents = (TextView) findViewById(R.id.presents);
        presents.setTypeface(typeface);

        up_layout = (LinearLayout) findViewById(R.id.up_layout);
        down_layout = (LinearLayout) findViewById(R.id.down_layout);
        left_to_wright = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left_to_right);
        right_to_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);

        up_layout.setAnimation(left_to_wright);
        down_layout.setAnimation(right_to_left);
        groupName.setAnimation(right_to_left);
        presents.setAnimation(left_to_wright);


        switchActivity();
    }

    public void switchActivity() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(r, 3000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


}

