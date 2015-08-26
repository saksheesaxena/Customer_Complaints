package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.admin.customer_complaints.R;

/**
 * Created by Sakshee on 05-Aug-15.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
finally {
                    Intent i  = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(i);

                }
            }

        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
