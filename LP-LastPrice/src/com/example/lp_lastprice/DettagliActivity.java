
package com.example.lp_lastprice;
import java.util.Date;
import android.app.Activity;
import database.*;
import android.widget.ImageButton;
import android.widget.TextView;
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
import android.os.Bundle; 
import android.widget.Toast;
// Activity di riepilogo contenente le informazioni dell'offerta selezionata
public class DettagliActivity extends Activity implements OnClickListener{
	String place;
	String lat;
	String lng;
	String descr;
	String seller;
	String user;
	String prezzo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettagli);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		final TextView text_desc = (TextView) findViewById(R.id.Description);
		final TextView text_price = (TextView) findViewById(R.id.Prezzo_form);
		final TextView text_seller = (TextView) findViewById(R.id.Seller_form);
		final TextView text_place = (TextView) findViewById(R.id.Luogo_form);
		Intent intent = getIntent();
		lat=intent.getStringExtra("latitudine");
		lng=intent.getStringExtra("longitudine");
		descr=intent.getStringExtra("descrizione");
		seller=intent.getStringExtra("venditore");
		user=intent.getStringExtra("username");
		text_desc.setText(intent.getStringExtra("descrizione"));
		text_price.setText(intent.getStringExtra("prezzo"));
		prezzo=intent.getStringExtra("prezzo");
		place=intent.getStringExtra("luogo");
		text_place.setText(intent.getStringExtra("luogo"));
		text_seller.setText(intent.getStringExtra("venditore"));
		ImageButton call=(ImageButton)findViewById(R.id.Call);
		call.setOnClickListener(this);
		Toast.makeText(this, "Connesso come: "+user, Toast.LENGTH_LONG).show();
		
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
		DbUsersHelper db=new DbUsersHelper(this);
		String tel=db.getTelephone(seller);
		Intent phoneCall = new Intent(Intent.ACTION_CALL);
		 String phNum = "tel:" + tel;
		 phoneCall.setData(Uri.parse(phNum));
		  startActivity(phoneCall) ;
	}
	public void openMaps(View view){
		Bundle bundle=new Bundle();
		bundle.putString("citta", place);
		bundle.putString("lat", lat);
		bundle.putString("lng", lng);
	 Intent intent = new Intent(this,MappaActivity.class);
	 intent.putExtras(bundle);
	 startActivity(intent);
 
}
	public void Buy(View view){
		DbUsersHelper db=new DbUsersHelper(this);
		 String date=new Date(System.currentTimeMillis()).toString();
		Vendita v=new Vendita(descr,seller,user,date);
		long i=db.insertSale(v);
		double j=db.updateUser(user,prezzo);
		Toast.makeText(this, "Credito attuale "+j, Toast.LENGTH_LONG).show();
		Uri uri = Uri.parse("http://www.visaitalia.com/carte-per-te/acquisti-online/verified-by-visa/");
		
        startActivity(new Intent( Intent.ACTION_VIEW, uri));
	}
}
