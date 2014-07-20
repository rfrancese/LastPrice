package com.example.lp_lastprice;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import database.DbUsersHelper;
import database.Utente;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.Button;
// Pagina 1 form registrazione
public class Registrazione extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_registrazione);

		 final Button button = (Button) findViewById(R.id.button1);
	        button.setOnClickListener(this);
	}

	@Override

		public void onClick(View view){
			
			String sesso="";
			 switch ( view.getId() ) {
	            case R.id.button1:
		
			EditText edit_name = (EditText)findViewById(R.id.editNome);
			 EditText edit_lname = (EditText)findViewById(R.id.editCognome);
			 EditText edit_birth = (EditText)findViewById(R.id.editData);
			EditText edit_user = (EditText)findViewById(R.id.editUser);
			 EditText edit_pass = (EditText)findViewById(R.id.editPassword);
			EditText edit_conf = (EditText)findViewById(R.id.editText6);
			RadioButton edit_radio = (RadioButton)findViewById(R.id.maschio);
			RadioButton edit_radiof = (RadioButton)findViewById(R.id.femmina);
			if (edit_radio.isChecked())sesso+="M";
			if (edit_radiof.isChecked())sesso+="F";
			if (((edit_name.getText().toString()).compareTo("")==0)||((edit_lname.getText().toString()).compareTo("")==0)||((edit_birth.getText().toString()).compareTo("")==0)||((edit_user.getText().toString()).compareTo("")==0)||((edit_pass.getText().toString()).compareTo("")==0)||((edit_radio.isChecked()==false)&&(edit_radiof.isChecked()==false)))
				Toast.makeText(this, "Compilare tutti i campi", Toast.LENGTH_LONG).show();
			else if ((edit_pass.getText().toString()).compareTo(edit_conf.getText().toString())!=0)
				 Toast.makeText(this, "La password non corrisponde", Toast.LENGTH_LONG).show();
			else {
				
		    Bundle bundle=new Bundle ();
		    bundle.putString("nome", edit_name.getText().toString());
		    bundle.putString("cognome", edit_lname.getText().toString());
		    bundle.putString("nascita", edit_birth.getText().toString());
		    bundle.putString("user", edit_user.getText().toString());
		    bundle.putString("pw", edit_pass.getText().toString());
		    bundle.putString("sesso", sesso);
			
			 Intent intent=new Intent(this, Registrazione2Activity.class);
			 intent.putExtras(bundle);
			startActivity(intent);
			break;
			}
		
		}
}
}
