package com.example.lp_lastprice;

import android.app.Activity;
import android.widget.TextView;
import database.DbUsersHelper;
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
// Home dell'applicazione
public class MainActivity extends Activity {
		private int id=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		DbUsersHelper db=new DbUsersHelper(this);
		TextView rate=(TextView)findViewById(R.id.Rate_T);
		TextView ratex=(TextView)findViewById(R.id.Rate);
		ratex.setVisibility(View.GONE);
		boolean b=db.isEmptyVote();
		if(b==false){
			double voto=db.getVoto();
			ratex.setVisibility(View.VISIBLE);
			rate.setText(String.valueOf(voto)+"/5.0");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MainActivity.this.finish();
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void createLogin(View view){
		Intent intent = new Intent (this,Login.class);
		startActivity(intent);
	}
	public void openOffer(View view){
	 Bundle bundle=new Bundle();
	 bundle.putInt("scelta", 1);
	 bundle.putString("username", "no user");
		Intent intent=new Intent (this, CatActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		}

	
}
