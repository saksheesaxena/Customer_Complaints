package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.customer_complaints.R;


public class ComplaintDescActivity extends BaseActivity {
    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;

    Button b4;
    Button b5;
    String[] complaint_department;
    Spinner spinner;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colplaint_desc);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

                        if (toolbar != null) {
                        setSupportActionBar(toolbar);
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                   }
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        spinner = (Spinner)findViewById(R.id.department_spinner);
        complaint_department = getResources().getStringArray(R.array.departments);
       b4.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
                startActivity(new Intent(ComplaintDescActivity.this, HomeActivity.class));
            }
       });
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    } */
    @Override
       protected int getLayoutResource() {
        return R.layout.activity_colplaint_desc;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
