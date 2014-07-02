
package com.example.lp_lastprice;

import android.app.Activity;
import android.widget.ImageButton;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.net.Uri;


public class DettagliActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettagli);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		ImageButton call=(ImageButton)findViewById(R.id.Call);
		call.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dettagli, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_dettagli,
					container, false);
			return rootView;
		}
	}
	public void onClick(View view){
		Intent phoneCall = new Intent(Intent.ACTION_CALL);
		 String phNum = "tel:" + "1234567890";
		 phoneCall.setData(Uri.parse(phNum));
		  startActivity(phoneCall) ;
	}
	public void openMaps(View view){
	 Intent intent = new Intent(this,MappaActivity.class);
	 startActivity(intent);
 
}
}
