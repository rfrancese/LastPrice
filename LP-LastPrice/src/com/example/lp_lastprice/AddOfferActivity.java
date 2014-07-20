package com.example.lp_lastprice;
import database.Coordinate;
import database.Offerta;
import database.DbUsersHelper;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;
import android.widget.Toast;
// Form inserimento nuova offerta per il seller
public class AddOfferActivity extends Activity {
	Coordinate coordinate[]=new Coordinate[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_offer);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		  coordinate[0]=new Coordinate(40.6695602,14.78563429999997, "Salerno");
	      coordinate[1]=new Coordinate(43.5671153,10.980700299999967, "Fucecchio");
	      coordinate[2]=new Coordinate(45.7224484,12.070272299999942, "Montecchio");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_offer, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_add_offer,
					container, false);
			return rootView;
		}
	}
	public void inserisci (View view){
		DbUsersHelper db=new DbUsersHelper(this);
		EditText edit_tit=(EditText)findViewById (R.id.titolo);
		EditText edit_price=(EditText)findViewById (R.id.prezzo);
		EditText edit_desc=(EditText)findViewById (R.id.Descrizione);
		EditText edit_place=(EditText)findViewById (R.id.Luogo);
		EditText edit_exp=(EditText)findViewById (R.id.Scadenza);
		EditText edit_cat=(EditText)findViewById (R.id.Categoria);
		String titolo=edit_tit.getText().toString();
		String prezzo=edit_price.getText().toString();
		String descr=edit_desc.getText().toString();
		String luogo=edit_place.getText().toString();
		String scadenza=edit_exp.getText().toString();
		String categoria=edit_cat.getText().toString();
		String price=prezzo;
		Bundle bundle=getIntent().getExtras();
		String venditore=bundle.getString("username");
		
		Offerta offer=new Offerta(titolo,descr,price,venditore,scadenza,luogo,categoria);
		for(int i=0 ; i<coordinate.length;i++){
			if (luogo.compareToIgnoreCase(coordinate[i].getCity())==0){
				offer.setLat(Double.toString(coordinate[i].getLat()));
				offer.setLng(Double.toString(coordinate[i].getLng()));
				break;
			}
			else {// da rimuovere
				offer.setLat(Double.toString(41.90911089999999));
				offer.setLng(Double.toString(12.467470899999967));
			}
		}
		long n= db.insertOffer(offer, "no");
		if (n==-1) Toast.makeText(this, "Impossibile Inserire l'elemento", Toast.LENGTH_LONG).show();
		else Toast.makeText(this, "Elemento inserito nei nostri Database! Offerta n."+n+" ",Toast.LENGTH_LONG).show();
		finish();
	}
}
