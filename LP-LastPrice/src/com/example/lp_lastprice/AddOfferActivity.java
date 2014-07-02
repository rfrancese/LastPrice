package com.example.lp_lastprice;
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

public class AddOfferActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_offer);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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
		String prezzo=edit_tit.getText().toString();
		String descr=edit_tit.getText().toString();
		String luogo=edit_tit.getText().toString();
		String scadenza=edit_tit.getText().toString();
		String categoria=edit_tit.getText().toString();
		double price=Double.parseDouble(prezzo);
		
		Offerta offer=new Offerta(titolo,descr,price,"mod",scadenza,luogo,categoria);
		db.insertOffer(offer, "no");
		
		
	}
}
