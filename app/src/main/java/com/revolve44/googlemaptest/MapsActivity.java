package com.revolve44.googlemaptest;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import static android.widget.Toast.LENGTH_SHORT;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener{

    private GoogleMap mMap;
    public LatLng lol;
    Marker marker;
    double latitude;
    double longitude;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        textView = findViewById(R.id.LOLIK);
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng latlng) {
//                // TODO Auto-generated method stub
//                lol = marker.getPosition();
//                textView.setText(lol+"");
//
//
//
//            }
//        });
        // Setting a click event handler for the map

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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        marker = googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .draggable(true)
                );
        mMap.setOnMarkerDragListener(this); // bridge for connect marker with methods located below
       // mMap.setOnMapLongClickListener(this);


        //lol = mMap.getPosition();
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"))
//        .isDraggable();
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        //setUpTracking();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                // create marker
                MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps");

// adding marker
                mMap.addMarker(marker);

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                 //Placing a marker on the touched position
                mMap.addMarker(markerOptions);

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, ""+latitude+" "+longitude, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDragStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Toast.makeText(MapsActivity.this, "onMarkerDrag", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        lol = marker.getPosition();
        textView.setText(lol+"");
        Toast.makeText(MapsActivity.this, ""+lol, Toast.LENGTH_SHORT).show();
    }









    public void ClickButton(View view) {
        try {
            textView.setText(lol+"");
        }catch (Exception e){
            textView.setText("Error ");
        }

    }


//    @Override
//    public void onMapClick(LatLng latLng) {
//        // Creating a marker
////        MarkerOptions markerOptions = new MarkerOptions();
////
////        // Setting the position for the marker
////        markerOptions.position(latLng);
////
////        // Setting the title for the marker.
////        // This will be displayed on taping the marker
////        markerOptions.title(latLng.latitude + " : " + latLng.longitude);
//
//        // Clears the previously touched position
//        Toast.makeText(MapsActivity.this, "CLICK", Toast.LENGTH_SHORT).show();
//        marker.remove();
//
//        // Animating to the touched position
//
//
//        // Placing a marker on the touched position
//        //mMap.addMarker(markerOptions);
//
//
////        latitude1= latLng.latitude;
////        longitude = latLng.longitude;
////        textView.setText(latitude1+"");
//    }
//    public void listeny(){
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//            }
//        });
//    }







    //mMap.setOnInfoWindowClickListener(getInfoWindowClickListener());


}



//    mMap.setOnMapClickListener(new OnMapClickListener(){
//        void onMapClick(LatLng point){
//            Toast.makeText(getContext(),
//                    point.latitude + ", " + point.longitude,
//                    Toast.LENGTH_SHORT).show();
//        }
//    });
//    //@Override
//    public void onMarkerDragEnd(Marker marker) {
//        // TODO Auto-generated method stub
//        Toast.makeText(
//                MapsActivity.this,
//                "Lat " + mMap.getMyLocation().getLatitude() + " "
//                        + "Long " + mMap.getMyLocation().getLongitude(),
//                Toast.LENGTH_LONG).show();
//        System.out.println("yalla b2a "
//                + mMap.getMyLocation().getLatitude());
//    }

//    public void onMapLongClick(LatLng point) {
//    Toast.makeText(MapsActivity.this, point.latitude+" "+point.longitude, Toast.LENGTH_SHORT).show();
//
//     }



