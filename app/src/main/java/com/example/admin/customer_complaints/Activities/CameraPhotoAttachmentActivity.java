package com.example.admin.customer_complaints.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.customer_complaints.R;

/**
 * Created by Sakshee on 15-Nov-15.
 */

public class CameraPhotoAttachmentActivity extends BaseActivity{
    ImageView uploaded_image , change_location_image_view;
    ImageButton attachment;
    LinearLayout upload_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_attachment);
        uploaded_image = (ImageView) findViewById(R.id.image_uploaded);
        Bundle extras = getIntent().getExtras();
        upload_layout = (LinearLayout) findViewById(R.id.upload_layout);
        upload_layout.setVisibility(View.INVISIBLE);
      //  attachment.setVisibility(View.INVISIBLE);
      //  change_location_image_view.setVisibility(View.INVISIBLE);

        if (extras != null) {
            String image_path = extras.getString("image_path");
            Toast.makeText(getApplicationContext(),image_path,Toast.LENGTH_LONG).show();

            Bitmap bm = decodeSampledBitmapFromPath(image_path, 500, 500);
            uploaded_image.setImageBitmap(bm);

        }
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
    protected int getLayoutResource() {
        return R.layout.activity_photo_attachment;
    }
}
