package com.example.lp_lastprice;

import android.app.Activity;
import database.*;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.RatingBar;
// Home venditore
public class SellerActivity extends Activity {
Bundle bundle;
String user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seller);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		bundle=getIntent().getExtras();
	 user=bundle.getString("username");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seller, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_seller,
					container, false);
			return rootView;
		}
	}
		public void openForm(View view){
			String user=bundle.getString("username");
			Intent intent = new Intent(this, AddOfferActivity.class);
			 Bundle bundle=new Bundle();
			 bundle.putString("username", user);
			 intent.putExtras(bundle);
			startActivity(intent);
		}
		public void openList(View view){
			
		
			Intent intent = new Intent (this, ActiveOffersActivity.class);
			intent.putExtra("username",user);
			startActivity(intent);
		}
		public void vota(View view){
			DbUsersHelper db=new DbUsersHelper(this);
			
			RatingBar voto=(RatingBar)findViewById(R.id.ratingBar1);
			Double x = Double.parseDouble(Float.toString(voto.getRating()));
			boolean b=db.isEmptyVote();
			double y=0;
			if(b){
				db.carica();
				}
			y=db.votaLP(x);
			Toast.makeText(this, "Grazie per aver votato i nostri servizi", Toast.LENGTH_LONG).show();
			
		}
		 public void logout(View view){
		      SharedPreferences sharedpreferences = getSharedPreferences
		      (Login.MyPREFERENCES, MODE_PRIVATE);
		      Editor editor = sharedpreferences.edit();
		      editor.clear();
		      editor.commit();
		     
		      moveTaskToBack(true); 
		       SellerActivity.this.finish();
		   }
		 public String getUser(){
			 return user;
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
