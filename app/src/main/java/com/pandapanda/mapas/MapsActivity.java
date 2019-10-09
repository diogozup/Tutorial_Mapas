package com.pandapanda.mapas;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Permissoes.validarPermissoes( permissoes,this,1)


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





        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //------------> change satellite/normal after

        // Minha Casa: 41.447390, -8.283380
        LatLng myHome = new LatLng(41.447390, -8.283380);

        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(myHome);
        circleOptions.radius(500); // Medida em metros
        circleOptions.strokeColor(10); // border
        circleOptions.strokeColor(Color.BLUE);
        circleOptions.fillColor(Color.argb(128, 0,191,255)); // opacity ranges 0 to 255 (0 = invs)

        mMap.addCircle(circleOptions);

/*
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(myHome);
        polygonOptions.add(latLang);
        polygonOptions.color(Color.red);
        polygonOptions.width(20);

        mMap.addPolyline(polygonOptions);
*/

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                double latitude = latLng.latitude;
                double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, "onClick Lat: "+latitude+"Long: "+longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Description")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                );
            }
        });


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                double latitude = latLng.latitude;
                double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, "onClick Lat: "+latitude+"Long: "+longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Description")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                );
            }
        });


        //--------------------------------------- INICIAR CAMARA EM MY HOME
        mMap.addMarker(
                new MarkerOptions()
                        .position(myHome)
                        .title("My Home")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.panda_ico))
                        /*.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))*/
        );

        // Value of zoom goes from 2.0 to 21.0
        mMap.moveCamera(
                CameraUpdateFactory
                        .newLatLngZoom(myHome, 15)
        );
    }
}
