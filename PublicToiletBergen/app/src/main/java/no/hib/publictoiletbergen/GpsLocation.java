package no.hib.publictoiletbergen;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by Steingalen on 11.02.2015.
 */
public class GpsLocation {

    MapsActivity getMarker;
    public PolylineOptions mrkr;

    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private final TextView gpsStatusTextView;
    private final Context mContext;
    private boolean canGetLocation;
    private double latitude;
    private double longitude;
    private Location location;
    private Location clickLocation;
    PolylineOptions polylineOptions = new PolylineOptions();



    public GpsLocation(Context mContext, TextView gpsStatusTextView) {
        this.mContext = mContext;
        this.gpsStatusTextView = gpsStatusTextView;
        getLocation();
    }

    public Location getLocation() {
        try {
            LocationManager locationManager = (LocationManager) mContext
                    .getSystemService(Context.LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, (android.location.LocationListener) this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, (android.location.LocationListener) this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }



/*
    GoogleMap.OnMapLongClickListener myOnMapLongClick =
            new GoogleMap.OnMapLongClickListener(){

                @Override
                public void onMapLongClick(LatLng point) {
                    getMarker.mMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title(point.toString()));

                    clickLocation = getMarker.mMap.getMyLocation();
                        polylineOptions.add(point);
                        polylineOptions.add(
                                new LatLng(clickLocation.getLatitude(), clickLocation.getLongitude()));
                        getMarker.mMap.addPolyline(polylineOptions);
                    }


            };*/


    public boolean getCanGetLocation() {
        return canGetLocation;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setCanGetLocation(boolean canGetLocation) { this.canGetLocation = canGetLocation; }
    public void setLatitude(double latitude) {this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

}

