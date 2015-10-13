package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.DatabaseHandler;
import com.example.admin.customer_complaints.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sakshee on 13-Sep-15.
 */
public class SignUpActivity extends Activity {


    EditText inputName, inputAddress, inputMobile, inputEmail;
    AppCompatButton inputGender;
    Spinner inputUlb ,inputDistrict;
    Button registerButton;
    String name, ulb, address, mobile, gender, email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        DatabaseHandler db = new DatabaseHandler(this);


        // Defining Layout items
        inputName = (EditText) findViewById(R.id.signup_name);
        inputAddress = (EditText) findViewById(R.id.signup_address);
        inputMobile = (EditText) findViewById(R.id.signup_mob);
        inputGender = (AppCompatButton) findViewById(R.id.signup_gender);
        inputEmail = (EditText) findViewById(R.id.signup_email);
        inputUlb = (Spinner) findViewById(R.id.signup_ulb);

        registerButton = (Button) findViewById(R.id.signup_register_btn);
        inputGender.setOnClickListener(new setGender());
     //   parentSpinner();



    registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputName.getText().toString();
                address = inputAddress.getText().toString();
                mobile = inputMobile.getText().toString();
                gender = inputGender.getText().toString();
                email = inputEmail.getText().toString();

                if (name.isEmpty() || ulb.isEmpty() || address.isEmpty() || mobile.isEmpty() || gender.isEmpty() || email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(SignUpActivity.this,HomeActivity.class));

            }

        });
    }



    //Async task to check whether Internet Connection is working

        private class Netcheck extends AsyncTask
        {
        private ProgressDialog nDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                nDialog = new ProgressDialog(SignUpActivity.this);
                nDialog.setMessage("Loading...");
                nDialog.setTitle("Checking Internet Connection");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
            }

           // Gets current device state and checks for working internet connection by trying Google.
            @Override
            protected Object doInBackground(Object[] params) {
                ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    try {
                        URL url = new URL("http://www.google.com");
                        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                        urlc.setConnectTimeout(3000);
                        urlc.connect();
                        if (urlc.getResponseCode() == 200) {
                            return true;
                        }
                    } catch (MalformedURLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return false;
            }


            }

    private class setGender implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new MaterialDialog.Builder(SignUpActivity.this)
                    .title(R.string.signup_gender)
                    .items(R.array.signup_gender_array)
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            inputGender.setText(text);
                        }
                    })
                    .positiveText("Select")
                    .show();

        }
    }


}




