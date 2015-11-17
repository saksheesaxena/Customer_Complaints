package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.customer_complaints.R;

/**
 * Created by Shilpy on 10/19/2015.
 */
public class CameraclickedActivity extends BaseActivity {
    Button cancel;
    Button next;
    Button button_location;
    String[] complaint_department;
    Spinner spinner;
    ImageView imgview;
    TextView txtview;
    String imgUri;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_pic);
        imgview = (ImageView) findViewById(R.id.imageView);
        txtview = (TextView) findViewById(R.id.textView6);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("KEY")) {
            imgUri= extras.getString("KEY");
        }
       // Bitmap bitmap = (Bitmap) intent.getParcelableExtra("bmp_img");
       // imgview.setImageBitmap(bitmap);
        cancel = (Button) findViewById(R.id.button4);
        next = (Button) findViewById(R.id.next);
        button_location = (Button)findViewById(R.id.button_location);
        spinner = (Spinner)findViewById(R.id.department_spinner);
        complaint_department = getResources().getStringArray(R.array.departments);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CameraclickedActivity.this, HomeActivity.class));
            }
        });

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CameraclickedActivity.this,MapActivity.class));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(CameraclickedActivity.this, PersonalDetailsActivity.class));

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
        return R.layout.activity_camera_pic;
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
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_home).setVisible(false);
        return true;
    }
}
