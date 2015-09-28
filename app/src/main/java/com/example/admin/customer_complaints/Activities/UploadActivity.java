package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.customer_complaints.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Shilpy on 9/14/2015.
 */
public class UploadActivity extends BaseActivity {
    private static final int PICK_IMAGE = 1;
    private static final int SELECT_PICTURE = 1;
    public ImageView imageview;
    String filename;
    String picturePath;
    public Button upload;
    public EditText caption;
    public Button next;
    public Bitmap bitmap;
    public ProgressDialog dialog;
    String selectedPath= "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_activity);
        caption = (EditText) findViewById(R.id.caption);
        next = (Button) findViewById(R.id.next);
        imageview = (ImageView) findViewById(R.id.imageView2);
        upload = (Button) findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PICTURE);



            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadActivity.this, ComplaintDescActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //  inflater.inflate(R.menu.imageupload_menu, menu);
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case SELECT_PICTURE:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageview.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.upload_activity;
    }


        }



