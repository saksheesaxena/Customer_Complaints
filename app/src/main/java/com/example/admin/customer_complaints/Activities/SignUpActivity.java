package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.R;
import com.example.admin.customer_complaints.library.LoginDataBaseAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Created by Sakshee on 13-Sep-15.
 */
public class SignUpActivity extends Activity {


    EditText inputName, inputAddress, inputMobile, inputEmail , inputGender;
    Spinner inputUlb ;
    Button signup_register_button;
    String name, address, mobile, gender, email;
    private Spinner typeSpinner;
    public String selected_ulb;
    LoginDataBaseAdapter loginDataBaseAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);



// get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter=loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Defining Layout items
        inputName = (EditText) findViewById(R.id.signup_name);
        inputAddress = (EditText) findViewById(R.id.signup_address);
        inputMobile = (EditText) findViewById(R.id.signup_mob);
        inputGender = (EditText) findViewById(R.id.signup_gender);
        inputGender.setOnClickListener(new setGender());
        inputEmail = (EditText) findViewById(R.id.signup_email);
        inputUlb = (Spinner) findViewById(R.id.signup_ulb);
        signup_register_button = (Button) findViewById(R.id.signup_register_btn);
        spinner();
        inputUlb.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected_ulb = inputUlb.getSelectedItem().toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

     signup_register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = inputName.getText().toString();
                address = inputAddress.getText().toString();
               mobile = inputMobile.getText().toString();
                gender = inputGender.getText().toString();
                email = inputEmail.getText().toString();
                if (name.isEmpty() || mobile.isEmpty() || address.isEmpty() ||
                        gender.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Some fields are missing.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(name, mobile,gender,address,email,selected_ulb);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    intent.putExtra("SELECTED_ULB", selected_ulb);
                    startActivity(intent);
                }



            }


        });

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
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
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            inputGender.setText(text);
                            return true;
                        }
                    })
                    .positiveText("Select")
                    .show();

        }
    }


    public void spinner()
    {
        ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.ulb_names));
        Dadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        inputUlb.setAdapter(Dadapter);

    }


}




