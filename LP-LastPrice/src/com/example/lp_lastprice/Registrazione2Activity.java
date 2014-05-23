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


public class Registrazione2Activity extends Activity implements OnClickListener{
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
		
        Bundle bundle = this.getIntent().getExtras();
        nome=bundle.getString("nome");
        cognome=bundle.getString("cognome");
        nascita=bundle.getString("nascita");
        user=bundle.getString("user");
        password=bundle.getString("password");
        sesso=bundle.getString("sesso");
        
        final Button button = (Button) findViewById(R.id.conferma_but);
        button.setOnClickListener(this);
  
		
	}

public void onClick (View view) {
	String check="";
	boolean b=true;
	EditText edit_iva=(EditText)findViewById (R.id.piva);
	EditText edit_carta=(EditText)findViewById (R.id.carta);
	RadioButton cliente=(RadioButton)findViewById (R.id.cl);
	RadioButton venditore=(RadioButton)findViewById (R.id.ve);
	RadioButton amministratore=(RadioButton)findViewById (R.id.amm);
	bundle=this.getIntent().getExtras();
	if (venditore.isChecked()) {
		if((edit_iva.getText().toString()).compareTo("")==0) {b=false;
		 Toast.makeText(this, "Inserire partita iva", Toast.LENGTH_LONG).show();
		}
		check="v";
		bundle.putString("iva", edit_iva.getText().toString());
		
	}
	if (cliente.isChecked()) {
		if((edit_carta.getText().toString()).compareTo("")==0) {b=false;
		 Toast.makeText(this, "Inserire numero di carta", Toast.LENGTH_LONG).show();
		}
		check="u";
		bundle.putString("carta", edit_carta.getText().toString());
		
	}
	if (amministratore.isChecked()) {
		if((edit_carta.getText().toString()).compareTo("")==0) {b=false;
		 Toast.makeText(this, "Inserire numero di carta", Toast.LENGTH_LONG).show();
		}
		 check="ad";
		 bundle.putString("carta", edit_carta.getText().toString());
	}
	
	bundle.putString("account", check);
	if(b){
	Intent intent = new Intent (this, RipeilogoActivity.class);
	intent.putExtras(bundle);
	startActivity (intent);
	}
}
}
