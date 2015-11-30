package com.example.admin.customer_complaints.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
    private static final int SELECT_PICTURE = 1;
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
    public String final_image_uri;
    TextView tv;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_selection);
        tv = (TextView) findViewById(R.id.test_tv);


        b1 = (Button) findViewById(R.id.button1);
       //b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        test = (ImageView) findViewById(R.id.test);
        //  button_location = (Button) findViewById(R.id.button_location);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            selectImage();
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ChoiceSelectionActivity.this, ComplaintDescActivity.class));
            }
        });
    }

        private void selectImage() {

            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

            AlertDialog.Builder builder = new AlertDialog.Builder(ChoiceSelectionActivity.this);
            builder.setTitle("Add Photo!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Take Photo"))
                    {
                        // create intent to take a picture and return control to the calling application
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File photo = new File(android.os.Environment.getExternalStorageDirectory(), "Pic.jpg");
                        //You save your image in your sdcard by name "Pic.jpg"


                        //  fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                        imageUri = Uri.fromFile(photo);  //Save uri that is path of your image
                        // start the image capture Intent
                        startActivityForResult(intent, 1);

                    }
                    else if (options[item].equals("Choose from Gallery"))
                    {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 2);

                    }
                    else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
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
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                // Image captured and saved to fileUri specified in the Intent
                new_image_uri = data.getData();
                final_photo_uri = new_image_uri.toString();

                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                new_file = getRealPathFromURI(ChoiceSelectionActivity.this, new_image_uri);
                Bitmap bm = decodeSampledBitmapFromPath(new_file, 500, 500);
                test.setImageBitmap(bm);

                Intent iSecond = new Intent(ChoiceSelectionActivity.this, PhotoattachmentActivity.class);
                iSecond.putExtra("image_path", new_file);
                startActivity(iSecond);

            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else if (requestCode == 2) {

                new_image_uri = data.getData();
                final_photo_uri = new_image_uri.toString();


                new_file = getRealPathFromURI(ChoiceSelectionActivity.this, new_image_uri);
                Bitmap bm = BitmapFactory.decodeFile(new_file);
                test.setImageBitmap(bm);
                Intent iSecond = new Intent(ChoiceSelectionActivity.this, PhotoattachmentActivity.class);
                iSecond.putExtra("image_path", new_file);
                startActivity(iSecond);

            }

            }
            // Image capture failed, advise user
        }

    public  Bitmap decodeSampledBitmapFromPath(String path, int reqWidth,
                                               int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        return bmp;

    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
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
