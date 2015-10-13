package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.R;

/**
 * Created by Sakshee on 05-Aug-15.
 */
public class Splash extends Activity implements Animation.AnimationListener {
    Button register_choice , wo_register;
    ImageView logo;
    TextView app_name;
    TextView app_desc;
Animation animFadeIn, animTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = (ImageView) findViewById(R.id.splash_logo);
        app_name = (TextView) findViewById(R.id.splash_welcome);
        app_desc = (TextView) findViewById(R.id.splash_app_description);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeIn.setAnimationListener(this);
        animTranslate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        logo.startAnimation(animFadeIn);
        app_name.startAnimation(animFadeIn);
        app_desc.startAnimation(animFadeIn);

        register_choice = (Button) findViewById(R.id.register_button);
        wo_register = (Button) findViewById(R.id.wo_button);

       register_choice.setVisibility(View.INVISIBLE);

        wo_register.setVisibility(View.INVISIBLE);









      Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
finally {


                    wo_register.getHandler().post(new Runnable() {
                        public void run() {
                            wo_register.setVisibility(View.VISIBLE);
                            wo_register.startAnimation(animFadeIn);
                        }
                    });
                    register_choice.getHandler().post(new Runnable() {
                        public void run() {
                            register_choice.setVisibility(View.VISIBLE);
                            register_choice.setAnimation(animFadeIn);
                        }
                    });
                    register_choice.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Splash.this,SignUpActivity.class));

                        }


                    });


                    wo_register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new MaterialDialog.Builder(Splash.this)
                                    .dividerColorRes(R.color.colorPrimary)
                                    .title(R.string.select_ulb)
                                    .items(R.array.ulb_names)
                                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallback() {
                                        @Override
                                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            Intent intent =  new Intent(Splash.this, HomeActivity.class);
                                            intent.putExtra("SELECTED_ULB",text);
                                            startActivity(intent);
                                          //  getDefaultulb(text);
                                           // inputGender.setText(text);
                                        }
                                    })
                                    .positiveText("Ok")
                                    .show();

                        }
                    });


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





    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
