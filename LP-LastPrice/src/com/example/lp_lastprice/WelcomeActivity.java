package com.example.lp_lastprice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.Intent;

import java.util.*;
// Home cliente
public class WelcomeActivity extends Activity {
	Bundle b;
	Bundle bundle;
	SessionManagement s;
	String user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_welcome,
					container, false);
			return rootView;
		}
	}
		public void openCat(View view){
			Bundle b=getIntent().getExtras();
			user=b.getString("username");
			 Bundle bundle=new Bundle();
			 bundle.putInt("scelta", 0);
			 bundle.putString("username", user);
			Intent intent = new Intent(this, CatActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
		
		 public void logout(View view){
		      SharedPreferences sharedpreferences = getSharedPreferences
		      (Login.MyPREFERENCES, MODE_PRIVATE);
		      Editor editor = sharedpreferences.edit();
		      editor.clear();
		      editor.commit();
		     
		      moveTaskToBack(true); 
		      WelcomeActivity.this.finish();
		   }
	public void openForm(View view){
		Intent intent = new Intent(this, RimborsoActivity.class);
		startActivity(intent);
	}
	 @Override
	 public void onDestroy(){
		 
		    SharedPreferences sharedpreferences = getSharedPreferences
				      (Login.MyPREFERENCES, MODE_PRIVATE);
				      Editor editor = sharedpreferences.edit();
				      editor.clear();
				      editor.commit();
				      super.onDestroy();
	 }
	 @Override
	 public void onPause(){
		
		    SharedPreferences sharedpreferences = getSharedPreferences
				      (Login.MyPREFERENCES, MODE_PRIVATE);
				      Editor editor = sharedpreferences.edit();
				      editor.clear();
				      editor.commit();
				     
				      super.onPause();
	 }
}
