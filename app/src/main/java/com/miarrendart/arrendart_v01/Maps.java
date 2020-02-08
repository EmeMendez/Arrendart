package com.miarrendart.arrendart_v01;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import com.miarrendart.arrendart_v01.Activities.NewHouse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Maps extends AppCompatActivity implements GoogleMap.OnMarkerDragListener,OnMapReadyCallback {

    private static final String TAG = "";
    private GoogleMap mMap;
    private Marker myMarker,draw;

    String placeId = "INSERT_PLACE_ID_HERE";
    AlertDialog alert = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);


        mapFragment.getMapAsync(this);






        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */

            // Initialize Places.
            Places.initialize(getApplicationContext(), "AIzaSyAJTzXeVVnwAOh2WtkHGNvsejP3HCLMFY0");
            // Create a new Places client instance.
            PlacesClient placesClient = Places.createClient(this);

// Create a new Places client instance.
        // Initialize the AutocompleteSupportFragment.
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment1);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

    }



        /*List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
        FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields)
                .build();
        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.OVERLAY, placeFields)
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

        */







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

        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }else {
            myLocation();
            // Add a marker in Sydney and move the camera
            //LatLng sydney = new LatLng(-34, 151);
            // LatLng sydney = new LatLng(-29.908416902396166, -71.25670932233334);
            //mMap.addMarker(new MarkerOptions().position(sydney).title("Mi ubicación"));
            //CameraPosition camera = CameraPosition.builder().target(sydney).zoom(16).bearing(0).tilt(45).build();
            // mMap.setMinZoomPreference(6.0f);
            // mMap.setMaxZoomPreference(15.0f);
            // mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));



       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(Map2.this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (ActivityCompat.checkSelfPermission(Map2.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Map2.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            }
        });*/


            LatLng drawer = new LatLng(NewHouse.latitude, NewHouse.longitude);
            CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(drawer, 16);
            draw = googleMap.addMarker(new MarkerOptions().position(drawer).title("Mueveme").draggable(true));
            draw.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mMap.animateCamera(myLocation);
            googleMap.setOnMarkerDragListener(this);
        }
    }



    private void addMarker(double lat, double lon) {
        LatLng coord = new LatLng(lat, lon);
        CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(coord, 16);
        if (myMarker != null) {
            myMarker.remove();
        }
        myMarker = mMap.addMarker(new MarkerOptions().position(coord).title("Mi Ubicación Actual"));
        mMap.animateCamera(myLocation);


    }

    private void updateLocation(Location location) {
        if (location != null) {
            NewHouse.latitude = location.getLatitude();
            NewHouse.longitude = location.getLongitude();
            //addMarker(Map2.latitude_blue, Map2.longitude_blue);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            updateLocation(location);
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
    };


    public void myLocation() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.updateLocation(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,10000,0,locationListener);

       LocationServices.getFusedLocationProviderClient(Maps.this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (ActivityCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Maps.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                   return;
                }
                mMap.setMyLocationEnabled(true);

            }
        });




    }


   /* @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        if (resultCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }*/


    @Override
    public void onMarkerDragStart(Marker marker) {
        if(marker.equals(draw)){
        }
    }
    @Override
    public void onMarkerDrag(Marker marker) {
        if(marker.equals(draw)){
            String newTitle  =String.format(Locale.getDefault(),
                    getString(R.string.detail_lat_lon),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);
        }
    }
    @Override
    public void onMarkerDragEnd(Marker marker) {
        if(marker.equals(draw)){
            NewHouse.latitude = marker.getPosition().latitude;
            NewHouse.longitude =marker.getPosition().longitude;
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> list = null;
            try {
                list = geocoder.getFromLocation(
                        NewHouse.latitude,NewHouse.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!list.isEmpty()) {
                Address DirCalle = list.get(0);
                NewHouse.p_latitude.setText(String.valueOf(marker.getPosition().latitude));
                NewHouse.p_longitude.setText(String.valueOf(marker.getPosition().longitude));
                Toast.makeText(this," Dirección: " + DirCalle.getAddressLine(0), Toast.LENGTH_LONG).show();


            }

        }

    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }


}
