package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.R;
import com.example.admin.customer_complaints.library.LoginDataBaseAdapter;

import java.sql.SQLException;

/**
 * Created by Sakshee on 05-Aug-15.
 */
public class Splash extends Activity {
    Button signin_button,signup_button;
    ImageView logo;
    TextView app_name, wo_register;
    TextView app_desc;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        logo = (ImageView) findViewById(R.id.splash_logo);
        app_name = (TextView) findViewById(R.id.splash_welcome);
        app_desc = (TextView) findViewById(R.id.splash_app_description);
        signin_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        wo_register = (TextView) findViewById(R.id.skip_text_view);

        final LinearLayout button_layout = (LinearLayout) findViewById(R.id.button_layout);
        final LinearLayout image_layout= (LinearLayout) findViewById(R.id.image_layout);
        button_layout.setVisibility(View.GONE);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter=loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                   final Animation animTranslate  = AnimationUtils.loadAnimation(Splash.this, R.anim.translate);
                    animTranslate.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation arg0) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation arg0) {
                        }

                        @Override
                        public void onAnimationEnd(Animation arg0) {
                            button_layout.setVisibility(View.VISIBLE);
                            Animation animFade = AnimationUtils.loadAnimation(Splash.this, R.anim.fade);
                            button_layout.startAnimation(animFade);
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            image_layout.startAnimation(animTranslate);
                        }
                    });



                }


                    signup_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Splash.this,SignUpActivity.class));

                        }


                    });
                signin_button.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         final Dialog dialog = new Dialog(Splash.this);
                                                         dialog.setContentView(R.layout.activity_signin);
                                                         dialog.setTitle("Login");
                                                         final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
                                                         final EditText editTextMobile = (EditText) dialog.findViewById(R.id.editTextMobileToLogin);

                                                         Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);
                                                         // Set On ClickListener
                                                         btnSignIn.setOnClickListener(new View.OnClickListener() {

                                                             public void onClick(View v) {
                                                                 // get The User name and Password
                                                                 String userName = editTextUserName.getText().toString();
                                                                 String mobile = editTextMobile.getText().toString();

                                                                 // fetch the Password form database for respective user name
                                                                 String storedMobile = loginDataBaseAdapter.getSinlgeEntry(userName);

                                                                 // check if the Stored password matches with  Password entered by user
                                                                 if (mobile.equals(storedMobile)) {
                                                                     Toast.makeText(Splash.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                                                                     dialog.dismiss();
                                                                 } else {
                                                                     Toast.makeText(Splash.this, "User Name or Mobile does not match", Toast.LENGTH_LONG).show();
                                                                 }
                                                             }
                                                         });

                                                         dialog.show();
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


            };
        timerThread.start();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }





}
