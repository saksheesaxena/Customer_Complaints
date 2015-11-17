package com.example.admin.customer_complaints.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.customer_complaints.R;

import java.io.File;

/**
 * Created by Sakshee on 24-Aug-15.
 */
public class ChoiceSelectionActivity extends BaseActivity {
    Button b1;
    Button b2;
    Button b3;
   // Button button_location;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public Uri imageUri;
    private Uri fileUri;
    private Intent intent;
    private ImageButton btn;
    private ImageView mImageView;
    String mCurrentPhotoPath;
    ImageView test;
    public Uri new_image_uri;
    public String final_photo_uri;
    public String new_file;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_selection);


        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        test = (ImageView) findViewById(R.id.test);
      //  button_location = (Button) findViewById(R.id.button_location);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
                //You save your image in your sdcard by name "Pic.jpg"


                //  fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                imageUri = Uri.fromFile(photo);  //Save uri that is path of your image


                // start the image capture Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);



            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChoiceSelectionActivity.this, PhotoattachmentActivity.class));
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChoiceSelectionActivity.this, ComplaintDescActivity.class));
            }
        });



    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                new_image_uri = data.getData();
                final_photo_uri = new_image_uri.toString();

                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                new_file = getRealPathFromURI(ChoiceSelectionActivity.this, new_image_uri);
                Bitmap bm = BitmapFactory.decodeFile(new_file);
                test.setImageBitmap(bm);
               Intent iSecond=new Intent(ChoiceSelectionActivity.this,CameraPhotoAttachmentActivity.class);
                iSecond.putExtra("image_path",new_file);
                startActivity(iSecond);

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }


    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_home).setVisible(false);
        return true;
    }



    @Override
    protected int getLayoutResource() {
        return R.layout.activity_choice_selection;
    }
}
