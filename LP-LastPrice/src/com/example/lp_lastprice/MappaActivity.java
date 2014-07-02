package com.example.lp_lastprice;

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


public class MappaActivity extends MapActivity{
	 private MapController mapController;
	    private MapView mapView;
	@Override
	protected boolean isRouteDisplayed() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	 
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
                
        mapController = mapView.getController();
     
 
    }
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
 
}
