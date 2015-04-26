package brettratner.com.safedriving;


/**
 * Created by BRETT on 4/8/15.
 */


import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapview extends FragmentActivity implements LocationListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();


        LocationManager locationManager;

// Get the LocationManager object from the System Service LOCATION_SERVICE
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

// Create a criteria object needed to retrieve the provider
        Criteria criteria = new Criteria();

// Get the name of the best available provider
        String provider = locationManager.getBestProvider(criteria, true);

// We can use the provider immediately to get the last known location
        Location location = locationManager.getLastKnownLocation(provider);

// request that the provider send this activity GPS updates every 5 seconds
        locationManager.requestLocationUpdates(provider, 5000, 0, this);

    }


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


    private void setUpMap() {
//        mMap.addCircle(new CircleOptions().strokeWidth(5).fillColor(155).center(new LatLng(40.269973, -74.776509)));
//        mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }
    private void drawMarker(Location location){
        mMap.clear();

//  convert the location object to a LatLng object that can be used by the map API
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        double latitude   = 40.2712;
        double longitude  = -74.7829;



// zoom to the current location
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition,16));

// add a marker to the map indicating our current position

        mMap.addMarker(new MarkerOptions()
                .position(currentPosition)
                .title("your location")
                );

    }
    @Override
    public void onLocationChanged(Location location) {
        if (mMap != null)
        {drawMarker(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}


