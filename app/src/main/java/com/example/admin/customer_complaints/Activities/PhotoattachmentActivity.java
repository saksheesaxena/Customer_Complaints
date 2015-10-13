package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.customer_complaints.R;

/**
 * Created by Shilpy on 10/13/2015.
 */
public class PhotoattachmentActivity extends BaseActivity {
    private static final int SELECT_PICTURE = 1;
    Button cancel;
    Button next;
    Button button_location;
    ImageButton attachment;
    TextView tv;
    String[] complaint_department;
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_attachment);
        cancel = (Button) findViewById(R.id.cut);
        next = (Button) findViewById(R.id.turn);
        tv = (TextView) findViewById(R.id.textView4);
        attachment = (ImageButton)findViewById(R.id.imageButton2);
        button_location = (Button)findViewById(R.id.button_location);
        spinner = (Spinner)findViewById(R.id.department_spinner);
        complaint_department = getResources().getStringArray(R.array.departments);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PhotoattachmentActivity.this, HomeActivity.class));
            }
        });

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhotoattachmentActivity.this,MapActivity.class));
            }
        });

        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PICTURE);



            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PhotoattachmentActivity.this, PersonalDetailsActivity.class));

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

   /*     switch(requestCode) {
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        } */

            switch(requestCode){
                case SELECT_PICTURE:
                    if (resultCode == RESULT_OK){
                        final Uri imageUri = data.getData();
                        tv.setText(imageUri.toString());

                        }



            }

        }



    /*  @Override
      public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_second, menu);
          return true;
      } */
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo_attachment;
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


