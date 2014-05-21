package com.example.lp_lastprice;

import android.app.Activity;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import database.Venditore;
import database.Utente;
import database.Amministratore;
import android.content.Intent;
public class Registrazione2Activity extends Activity {
   private String nome;
   private String cognome;
   private String nascita;
   private String user;
   private String password;
   private String sesso;
   private Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_registrazione2);

	
	bundle=this.getIntent().getExtras();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrazione2, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_registrazione2,
					container, false);
			return rootView;
		}
	}
public void welcome (View view) {
	EditText edit_iva=(EditText)findViewById (R.id.piva);
	EditText edit_carta=(EditText)findViewById (R.id.carta);
	RadioButton cliente=(RadioButton)findViewById (R.id.cl);
	RadioButton venditore=(RadioButton)findViewById (R.id.ve);
	RadioButton amministratore=(RadioButton)findViewById (R.id.amm);

	if (venditore.isChecked()) {
		
		String iva=edit_iva.getText().toString();
		Venditore v=new Venditore (nome, cognome, nascita, sesso, user, password, iva);
		
	}
	if (cliente.isChecked()) {
		
		String carta=edit_carta.getText().toString();
		
		Utente v=new Utente (nome, cognome, nascita, sesso, user, password, carta);
		
	}
	if (amministratore.isChecked()) {
		
		String carta=edit_carta.getText().toString();
		Amministratore v=new Amministratore (nome, cognome, nascita, sesso, user, password, carta);
		
	}
	
	Intent intent = new Intent (this, RipeilogoActivity.class);
	startActivity (intent);
}
}
