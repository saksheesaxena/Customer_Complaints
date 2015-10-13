package com.example.admin.customer_complaints.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.customer_complaints.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sakshee on 25-Aug-15.
 */
public class ExIfActivity extends BaseActivity {


    String  imagefile = "/storage/extSdCard/DCIM/Camera/20150821_234409.jpg";
    ImageView image;
    TextView ExIf;
    TextView addr;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exif);



        image = (ImageView) findViewById(R.id.image);
        ExIf = (TextView) findViewById(R.id.exif);
        addr = (TextView) findViewById(R.id.address);
        Bitmap bm = BitmapFactory.decodeFile(imagefile);
        image.setImageBitmap(bm);
        ExIf.setText(ReadExif(imagefile));
        getMyLocationAddress();



    }

    String ReadExif(String file){
        String exif="Exif: " + file;
        try {
            ExifInterface exifInterface = new ExifInterface(file);

            exif += "\n TAG_GPS_LATITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String attrLATITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE) ;
            exif += "\n TAG_GPS_LATITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String attrLATITUDE_REF  = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            exif += "\n TAG_GPS_LONGITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String attrLONGITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            exif += "\n TAG_GPS_LONGITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            String attrLONGITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            geoDegree gD = new geoDegree(exifInterface);
            gD.convertToDegree(attrLATITUDE);
            exif += "\n TAG_Latitude_decimal " + gD.convertToDegree(attrLATITUDE);
            latitude = gD.convertToDegree(attrLATITUDE);
            gD.convertToDegree(attrLONGITUDE);
            exif+= "\n Tag_Longitude_decimal"+ gD.convertToDegree(attrLONGITUDE);
            longitude = gD.convertToDegree(attrLONGITUDE);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(ExIfActivity.this,
                    e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        return exif;




    }


    public void getMyLocationAddress() {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        try {

            //Place your latitude and longitude
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude, 1);

            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }

                addr.setText("I am at: " + strAddress.toString());

            } else
                addr.setText("No location found..!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not get address..!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_exif;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_home).setVisible(false);
        return true;
    }
}
