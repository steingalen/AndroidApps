package no.hib.publictoiletbergen;

import android.graphics.Point;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.maps.GoogleMap.OnMapClickListener;

public class MapsActivity extends FragmentActivity  { //Resource for GPS: http://www.codeproject.com/Tips/678431/Example-of-Using-Google-Maps-Service-and-GPS-in-An
    public GoogleMap mMap; // = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap(); // Might be null if Google Play services APK is not available.
    public Marker marker;

//    public PolylineOptions mrkr;
/* first try, was outdated, but can't be too sure
    LocationRequest locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    private ConnectionCallbacks mConnectionCallbacks;
    private ConnectionCallbacks mOnConnectionFailedListener;
    GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .addConnectionCallbacks(mConnectionCallbacks)
            .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) mOnConnectionFailedListener)
            .build();*/

    private TextView gpsStatusTextView;
    GpsLocation gpsLocation = new GpsLocation(this, gpsStatusTextView);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        mMap.setOnMapClickListener((OnMapClickListener) gpsLocation.myOnMapLongClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(60.3901742, 5.3232455), 13)); //starter mappet ved koordinatene til bergen

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        mMap.addMarker(new MarkerOptions() //couldn't find out how to import the converted .csv file to .kml
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.3920969, 5.3281283)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.394554, 5.324099)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.392209, 5.324011)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.392928299999902, 5.3245788)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.392893099999903, 5.3266299)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.3888359, 5.3341752)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.388298, 5.333801)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.394116, 5.32278)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.397359, 5.313963)));
        mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.toilets))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(60.397557, 5.307858)));



        if (mMap == null) {
            Toast.makeText(getApplicationContext(),
                    "Not able to create maps", Toast.LENGTH_SHORT).show();
        }
        else {
            // Changing map type
            mMap.setMyLocationEnabled(true);
           // mMap.setOnMapClickListener((OnMapClickListener) gpsLocation.onMapLongClick);
        }

        if (gpsLocation.getCanGetLocation()){
            double longitude = gpsLocation.getLongitude();
            double latitude = gpsLocation.getLatitude();
        }
    }
}
