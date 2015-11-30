package com.example.admin.customer_complaints.Activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.customer_complaints.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Shilpy on 10/13/2015.
 */
public class PhotoattachmentActivity extends BaseActivity{
    private static final int SELECT_PICTURE = 1;
    Button cancel;
    Button next;
    Button button_location;
    ImageButton attachment;
    TextView tv,show_tv,address_tv;
    ImageView uploaded_image, change_location_image_view;
    String[] complaint_department;
    Spinner dept_spinner ,category_spinner;
    public String final_image_uri;
    double my_latitude, my_longitude;
    public String new_file, location,add,new_address;
    public String attrLATITUDE,attrLONGITUDE;
    LinearLayout address_layout;
    Integer i=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_attachment);
        dept_spinner = (Spinner) findViewById(R.id.departmentSpinner);
        category_spinner = (Spinner)findViewById(R.id.categorySpinner);
        uploaded_image = (ImageView) findViewById(R.id.image_uploaded);
        change_location_image_view = (ImageView) findViewById(R.id.change_location_image_view);
        cancel = (Button) findViewById(R.id.cut);
        next = (Button) findViewById(R.id.turn);
        tv = (TextView) findViewById(R.id.textView4);
        address_tv = (TextView)findViewById(R.id.address_text_view);
        show_tv = (TextView) findViewById(R.id.textView5);
        attachment = (ImageButton)findViewById(R.id.imageButton2);
        address_layout = (LinearLayout)findViewById(R.id.address_layout);
        parentSpinner();
      //  button_location = (Button)findViewById(R.id.button_location);
        complaint_department = getResources().getStringArray(R.array.departments_name);
       address_layout.setVisibility(View.INVISIBLE);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            new_address = extras.getString("CHANGED_LOCATION");
            address_layout.setVisibility(View.VISIBLE);
            address_tv.setText(new_address);



        }
        if (extras != null) {
            String image_path = extras.getString("image_path");
            Toast.makeText(getApplicationContext(),image_path,Toast.LENGTH_LONG).show();

            Bitmap bm = decodeSampledBitmapFromPath(image_path, 500, 500);
            uploaded_image.setImageBitmap(bm);
            Uri uri = Uri.parse(image_path);
      //      new_file = getRealPathFromURI(PhotoattachmentActivity.this, uri);
            ReadExif(image_path);
            address_layout.setVisibility(View.VISIBLE);
            getMyLocationAddress();

        }
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PhotoattachmentActivity.this, HomeActivity.class));
            }
        });

        change_location_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlist = new Intent(getApplicationContext(),MapActivity.class);
                Bundle b = new Bundle();
                b.putDouble("lat", my_latitude);//latitude
                b.putDouble("long", my_longitude);//longitude
                intentlist.putExtras(b);
                startActivity(intentlist);


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


        public void parentSpinner() {
        ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.departments_name));
        Dadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dept_spinner.setAdapter(Dadapter);
        dept_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {int selectedDept = position+1;
                DeptSpinner(selectedDept); }
            public void onNothingSelected(AdapterView<?> parent) {}
                                               }
                                 );
               }

    private int getDistrictResourceId ( int districtnr ) {
                   int resId = R.array.market_dept;
                    switch ( districtnr )
                    {
                                case 1:
                                resId = R.array.market_dept;
                                break;
                        case 2:
                                resId = R.array.signup_gender_array;
                               break;
                        // please add the rest
                                    }

                          return resId;
                }

                    public void DeptSpinner ( int districtnr) {

                            int resId = getDistrictResourceId( districtnr );

                            ArrayAdapter<String> Cadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(resId));
                   Cadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                    category_spinner.setAdapter(Cadapter);
                        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                                               }
                                                                            public void onNothingSelected(AdapterView<?> parent) {
                                                                                }
                                                                        }
                                    );
                }





    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("imageFile", new_file);


        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        savedInstanceState.getString("imageFile");


        super.onRestoreInstanceState(savedInstanceState);
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
                    try {
                        if (resultCode == RESULT_OK) {
                            final Uri imageUri = data.getData();
                            final_image_uri = imageUri.toString();
                            tv.setText(final_image_uri);
                            new_file = getRealPathFromURI(PhotoattachmentActivity.this, imageUri);
                            //     Bitmap bm = BitmapFactory.decodeFile(new_file);
                            //    uploaded_image.setImageBitmap(bm);
                            Bitmap bm = decodeSampledBitmapFromPath(new_file, 500, 500);
                            uploaded_image.setImageBitmap(bm);
                            ReadExif(new_file);
                            address_layout.setVisibility(View.VISIBLE);
                            getMyLocationAddress();
                        }

                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Location Unavailable",Toast.LENGTH_LONG).show();
                    }


                    }




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


    public void getMyLocationAddress() {

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

        try {

            //Place your my_latitude and my_longitude
            List<Address> addresses = geocoder.getFromLocation(my_latitude, my_longitude, 1);



            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }
                add = strAddress.toString();
               address_tv.setText("This photo is taken at:" + add);



            } else
                Toast.makeText(getApplicationContext(),"No Location Found" , Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
        }
    }

    String ReadExif(String file){
        String exif="Exif: " + file;
        try {
            ExifInterface exifInterface = new ExifInterface(file);

            //exif += "\n TAG_GPS_LATITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            attrLATITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
           // exif += "\n TAG_GPS_LATITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String attrLATITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            //exif += "\n TAG_GPS_LONGITUDE: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            attrLONGITUDE = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
          //  exif += "\n TAG_GPS_LONGITUDE_REF: " + exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
           // String attrLONGITUDE_REF = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
            geoDegree gD = new geoDegree(exifInterface);
            gD.convertToDegree(attrLATITUDE);
            //exif += "\n TAG_Latitude_decimal " + gD.convertToDegree(attrLATITUDE);
            my_latitude = gD.convertToDegree(attrLATITUDE);
            gD.convertToDegree(attrLONGITUDE);
           // exif += "\n Tag_Longitude_decimal" + gD.convertToDegree(attrLONGITUDE);
            my_longitude = gD.convertToDegree(attrLONGITUDE);





        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(PhotoattachmentActivity.this,
                    e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        return exif;




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


