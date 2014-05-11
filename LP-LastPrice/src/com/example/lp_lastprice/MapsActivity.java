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
public class MapsActivity extends MapActivity{
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_maps);
	    MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	}
}