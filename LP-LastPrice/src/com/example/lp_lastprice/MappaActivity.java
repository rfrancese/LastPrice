package com.example.lp_lastprice;
import android.support.v4.app.FragmentActivity;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import com.google.android.maps.MapActivity ;
import com.google.android.maps.MapView;
import com.google.android.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import android.widget.Toast;
import com.google.android.maps.MapController;
import com.google.android.gms.maps.model.LatLng; 
import com.google.android.gms.maps.model.Marker; 
import com.google.android.gms.maps.model.MarkerOptions;
// Google API per la visualizzazione di punti sulla mappa
public class MappaActivity extends FragmentActivity{
	   // Google Map
    private GoogleMap googleMap;
    String c;
    String lat;
    String lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle bundle = this.getIntent().getExtras();
         c=bundle.getString("citta");
        lat=bundle.getString("lat");
        lng=bundle.getString("lng");
        try {
            // Loading map
            initilizeMap(c,lat,lng);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap(String city ,String lat,String lng) {
        if (googleMap == null) {
            googleMap = ((MapFragment)getFragmentManager().findFragmentById( R.id.map)).getMap();
            
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng CIU=new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        Marker ciu = googleMap.addMarker(new MarkerOptions().position(CIU).title(city));
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap(c,lat,lng);
    }
 
}

