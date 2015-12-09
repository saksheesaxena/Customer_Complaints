package com.example.admin.customer_complaints.Activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.admin.customer_complaints.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Sakshee on 26-Sep-15.
 */
public class MapActivity extends FragmentActivity implements GoogleMap.OnMapClickListener,GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerDragListener {

    GoogleMap googleMap;
    boolean markerClicked;
    TextView tvLocInfo,address_tv;
    Marker marker;
    String present_location,changed_address = null;
    double newlat, newlng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show error dialog if Google PlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.activity_map);
        tvLocInfo = (TextView)findViewById(R.id.locinfo);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = supportMapFragment.getMap();
       // googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(googleMap.MAP_TYPE_HYBRID);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        markerClicked = false;
    /*  LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
        */
        Bundle b = getIntent().getExtras();
        double palatitude = b.getDouble("lat");
        double palongitude = b.getDouble("long");
        TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        LatLng point = new LatLng(palatitude,palongitude);
        googleMap.addMarker(new MarkerOptions()
                .position(point)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//        marker.showInfoWindow();

        // googleMap.setOnMarkerDragListener(getnewLocation());
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        // locationTv.setText("Latitude:" + lat + ", Longitude:" + lng);

        //  Toast.makeText(getApplicationContext(),""+palatitude + palongitude, Toast.LENGTH_LONG).show();

        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {

            //Place your my_latitude and my_longitude
            List<Address> addresses = geocoder.getFromLocation(palatitude,palongitude, 1);

            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }
                present_location = strAddress.toString();


                locationTv.setText("I am at: " + strAddress.toString());

            } else
                locationTv.setText("No location found..!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not get address..!", Toast.LENGTH_LONG).show();
        }


    }






    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }




    @Override
    public void onMapClick(LatLng point) {
        tvLocInfo.setText(point.toString());
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
        markerClicked = false;
    }


    @Override
    public void onMapLongClick(LatLng point) {
        if (marker != null) {
            marker.remove();
        }


        tvLocInfo.setText("New marker added@" + point.toString());
        Location location = new Location("Test");
        location.setLatitude(point.latitude);
        location.setLongitude(point.longitude);
        double changed_lat = location.getLatitude();


        double changed_lng = location.getLongitude();
        if ((changed_lat > 26.87 || changed_lat <21.2 ) && (changed_lng > 82.49  || changed_lng < 74.02 )) {
            Toast.makeText(getApplicationContext(), "Selected location is outside Madhya Pradesh", Toast.LENGTH_LONG).show();
        } else {
            Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
            try {

                //Place your my_latitude and my_longitude
                List<Address> addresses = geocoder.getFromLocation(changed_lat, changed_lng, 1);

                if (addresses != null) {

                    Address fetchedAddress = addresses.get(0);
                    final StringBuilder strAddress = new StringBuilder();

                    for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                        strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                    }
                    present_location = strAddress.toString();

                    new MaterialDialog.Builder(this)

                            .title(R.string.Confirm)
                            .content("You have selected the location:" + present_location)
                            .positiveText(R.string.Agree)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    tvLocInfo.setText("I am at: " + strAddress.toString());
                                    changed_address = strAddress.toString();
                                    Intent intent = new Intent(MapActivity.this, PhotoattachmentActivity.class);
                                    intent.putExtra("CHANGED_LOCATION", changed_address);
                                    startActivity(intent);


                                }
                            })
                            .negativeText(R.string.Disagree)
                            .show();


                } else
                    tvLocInfo.setText("No location found..!");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not get address..!", Toast.LENGTH_LONG).show();
            }


            marker = googleMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title("New Location")
                    .snippet(changed_address)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .draggable(true)
                    .visible(true));

            markerClicked = false;
            marker.showInfoWindow();


        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {

        tvLocInfo.setText("Marker " + marker.getId() + " Drag@" + marker.getPosition());
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        tvLocInfo.setText("Marker " + marker.getId() + " DragEnd");
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        tvLocInfo.setText("Marker " + marker.getId() + " DragStart");

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_home).setVisible(false);
        return true;
    }


}
