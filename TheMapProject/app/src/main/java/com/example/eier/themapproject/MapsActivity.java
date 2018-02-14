package com.example.eier.themapproject;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final  LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final  LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final  LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<Marker> markerList = new ArrayList<>();

        mMap.setMapType(googleMap.MAP_TYPE_HYBRID);

        mPerth = mMap.addMarker(new MarkerOptions().position(PERTH).title("Perth"));
        mPerth.setTag(0);
        markerList.add(mPerth);

        mSydney = mMap.addMarker(new MarkerOptions().position(BRISBANE).title("Brisbane")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mSydney.setTag(0);
        markerList.add(mSydney);
        mBrisbane = mMap.addMarker(new MarkerOptions().position(SYDNEY).title("Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mBrisbane.setTag(0);
        markerList.add(mBrisbane);


        mMap.setOnMarkerClickListener(this);
        for (Marker marker : markerList){
            LatLng latLng = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,2 ));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2));
        }

       /* // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng kilimonjaro = new LatLng(-3.067385, 37.3534432);

        mMap.addMarker(new MarkerOptions().position(kilimonjaro).title("Marker in Kilimanjaro")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kilimonjaro, 18));*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        if (clickCount != null){
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this, marker.getTitle() + " Has been clicked " + clickCount + " times ", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
