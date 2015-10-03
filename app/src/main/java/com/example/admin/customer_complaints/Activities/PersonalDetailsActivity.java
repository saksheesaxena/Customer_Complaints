package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.admin.customer_complaints.R;

/**
 * Created by Shilpy on 10/3/2015.
 */
public class PersonalDetailsActivity extends BaseActivity {
    Button submit;
    Button back;
    private Toolbar toolbar;
    Button button_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        submit = (Button) findViewById(R.id.submit);
        back = (Button) findViewById(R.id.back);
        button_location = (Button)findViewById(R.id.button_location);
        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalDetailsActivity.this,MapActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PersonalDetailsActivity.this, ComplaintDescActivity.class));

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PersonalDetailsActivity.this, HomeActivity.class));

            }
        });

    }
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_personal_details;
    }
}