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
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import database.DbUsersHelper;
import database.Utente;
import android.widget.RadioButton;
public class Registrazione extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrazione);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registrazione, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_registrazione,
					container, false);
			return rootView;
		}
	}
		public void welcome(View view){
			String sesso="";
			EditText edit_name = (EditText)findViewById(R.id.editNome);
			 EditText edit_lname = (EditText)findViewById(R.id.editCognome);
			 EditText edit_birth = (EditText)findViewById(R.id.editData);
			EditText edit_user = (EditText)findViewById(R.id.editUser);
			 EditText edit_pass = (EditText)findViewById(R.id.editPassword);
			RadioButton edit_radio = (RadioButton)findViewById(R.id.maschio);
			RadioButton edit_radiof = (RadioButton)findViewById(R.id.femmina);
			if (edit_radio.isChecked())sesso+="M";
			if (edit_radiof.isChecked())sesso+="F";
			
		    Bundle bundle=new Bundle ();
		    bundle.putString("nome", edit_name.getText().toString());
		    bundle.putString("cognome", edit_lname.getText().toString());
		    bundle.putString("nascita", edit_birth.getText().toString());
		    bundle.putString("user", edit_user.getText().toString());
		    bundle.putString("pw", edit_pass.getText().toString());
		    bundle.putString("sesso", sesso);
			
			 Intent intent=new Intent(this, Registrazione2Activity.class);
			startActivity(intent);
		}
}
