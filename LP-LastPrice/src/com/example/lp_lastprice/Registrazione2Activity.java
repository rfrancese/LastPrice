package com.example.lp_lastprice;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioButton;
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
import database.Venditore;
import database.Utente;
import database.Amministratore;
import android.content.Intent;
import database.*;
// Pagina 2 form registrazione
public class Registrazione2Activity extends Activity implements OnClickListener{
   private String nome;
   private String cognome;
   private String nascita;
   private String user;
   private String password;
   private String sesso;
   private Bundle bundle;
   private int n;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_registrazione2);
		
        Bundle bundle = this.getIntent().getExtras();
        nome=bundle.getString("nome");
        cognome=bundle.getString("cognome");
        nascita=bundle.getString("nascita");
        user=bundle.getString("user");
        password=bundle.getString("password");
        sesso=bundle.getString("sesso");
        n=0;
        final Button button = (Button) findViewById(R.id.conferma_but);
        button.setOnClickListener(this);
  
		
	}

public void onClick (View view) {
	String check="";
	boolean b=true;
	boolean bool=true;
	EditText edit_iva=(EditText)findViewById (R.id.piva);
	EditText edit_carta=(EditText)findViewById (R.id.carta);
	EditText edit_telephone=(EditText)findViewById (R.id.telefono);
	EditText edit_address=(EditText)findViewById (R.id.Address);
	EditText edit_mail=(EditText)findViewById (R.id.Mail);
	RadioButton cliente=(RadioButton)findViewById (R.id.cl);
	RadioButton venditore=(RadioButton)findViewById (R.id.ve);
	RadioButton amministratore=(RadioButton)findViewById (R.id.amm);
	
	bundle=this.getIntent().getExtras();
	if (venditore.isChecked()) {
		if(((edit_iva.getText().toString()).compareTo("")==0)||((edit_telephone.getText().toString()).compareTo("")==0)||((edit_address.getText().toString()).compareTo("")==0)||((edit_mail.getText().toString()).compareTo("")==0)) 
		{b=false;
		 Toast.makeText(this, "Compilare tutti i campi", Toast.LENGTH_LONG).show();
		}
		check="v";
		n=2;

		DbUsersHelper db=new DbUsersHelper(this);
		bool=db.validate(user, n);
		if(bool){
			Toast.makeText(this, "User già esistente, cambia nome", Toast.LENGTH_LONG).show();
		}
		
		bundle.putString("iva", edit_iva.getText().toString());
		
	}
	if (cliente.isChecked()) {
		if(((edit_carta.getText().toString()).compareTo("")==0)||((edit_telephone.getText().toString()).compareTo("")==0)||((edit_address.getText().toString()).compareTo("")==0)||((edit_mail.getText().toString()).compareTo("")==0)) 
		{b=false;
		 Toast.makeText(this, "Compilare tutti i campi", Toast.LENGTH_LONG).show();
		}
		check="u";
		n=1;
		DbUsersHelper db=new DbUsersHelper(this);
		bool=db.validate(user, n);
		if(bool){
			Toast.makeText(this, "User già esistente, cambia nome", Toast.LENGTH_LONG).show();
		}
		bundle.putString("carta", edit_carta.getText().toString());
		
	}
	if (amministratore.isChecked()) {
		if(((edit_carta.getText().toString()).compareTo("")==0)||((edit_telephone.getText().toString()).compareTo("")==0)||((edit_address.getText().toString()).compareTo("")==0)||((edit_mail.getText().toString()).compareTo("")==0)) 
		{b=false;
		 Toast.makeText(this, "Compilare tutti i campi", Toast.LENGTH_LONG).show();
		}
		 check="ad";
		 n=3;
			DbUsersHelper db=new DbUsersHelper(this);
			bool=db.validate(user, n);
			if(bool){
				Toast.makeText(this, "User già esistente, cambia nome", Toast.LENGTH_LONG).show();
			}
		 bundle.putString("carta", edit_carta.getText().toString());
	}
	
	bundle.putString("account", check);
	bundle.putString("telefono", edit_telephone.getText().toString());
	bundle.putString("indirizzo", edit_address.getText().toString());
	bundle.putString("mail", edit_mail.getText().toString());
	if(b&&(bool==false)){
	Intent intent = new Intent (this, RipeilogoActivity.class);
	intent.putExtras(bundle);
	startActivity (intent);
	}

}
}
