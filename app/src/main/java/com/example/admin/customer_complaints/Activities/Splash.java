package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.customer_complaints.R;

/**
 * Created by Sakshee on 05-Aug-15.
 */
public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Button register_choice;
        TextView wo_register;

        register_choice = (Button) findViewById(R.id.register_button);
        register_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(Splash.this,HomeActivity.class));
            }
        });


        wo_register = (TextView) findViewById(R.id.continue_wo_register);
        wo_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash.this,HomeActivity.class));
            }
        });
       /* Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
finally {
                    Intent i  = new Intent(getBaseContext(),HomeActivity.class);
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
    */


    }
}
