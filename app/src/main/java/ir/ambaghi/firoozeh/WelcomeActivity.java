package ir.ambaghi.firoozeh;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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
        handler.postDelayed(r, 5000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }


}

