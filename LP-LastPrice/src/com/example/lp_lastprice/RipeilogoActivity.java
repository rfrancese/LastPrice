package com.example.lp_lastprice;

import database.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;

public class RipeilogoActivity extends Activity implements OnClickListener{
	
   private Bundle bundle;
   private String account="";
   private String nome="";
   private String cognome="";
   private String nascita="";
   private String sesso="";
   private String user="";
   private String password="";
   private String carta_iva="";
   private int check=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_ripeilogo);
		final Button conferma=(Button)findViewById(R.id.confirm);
		conferma.setOnClickListener(this);
	        final TextView text_name = (TextView) findViewById(R.id.nome_r);
	        final TextView text_lastname = (TextView) findViewById(R.id.cognome_r);
	        final TextView text_birth = (TextView) findViewById(R.id.data_r);
	        final TextView text_sex = (TextView) findViewById(R.id.sesso_r);
	        final TextView text_user = (TextView) findViewById(R.id.user_r);
	        final TextView text_pass = (TextView) findViewById(R.id.password_r);
	        final TextView text_iva = (TextView) findViewById(R.id.carta_iva);
	        final TextView text_account = (TextView) findViewById(R.id.account_r);
	        Bundle bundle = this.getIntent().getExtras();
	        nome=bundle.getString("nome");
	        cognome=bundle.getString("cognome");
	        nascita=bundle.getString("nascita");
	        user=bundle.getString("user");
	        sesso=bundle.getString("sesso");
	        password=bundle.getString("pw");
	        account=bundle.getString("account");
	        carta_iva=bundle.getString("carta");
	        text_name.setText(nome);
	        text_lastname.setText(cognome);
	        text_birth.setText(nascita);
	        text_sex.setText(sesso);
	        text_user.setText(user);
	        text_pass.setText(password);
	        text_iva.setText(carta_iva);
	        text_account.setText(account);
	        account=bundle.getString("account");
	        
	}
public void onClick(View view) {
	Intent intent=new Intent(this,WelcomeActivity.class);
	
	 switch ( view.getId() ) {
     case R.id.confirm:
    	 if (account.compareToIgnoreCase("v")==0){
    		 check=2;
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva);
    		 DbUsersHelper db= new DbUsersHelper(this);
     		db.addUser(u, check);
			intent = new Intent(this,SellerActivity.class);
    	 }
    	 if (account.compareToIgnoreCase("u")==0){
    		 check=1;// tipologia account utente
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva);
    		DbUsersHelper db= new DbUsersHelper(this);
    		db.addUser(u, check);
 			intent = new Intent(this,WelcomeActivity.class);
     	 }
    	 if (account.compareToIgnoreCase("ad")==0){
    		 check=3;
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva);
    		 DbUsersHelper db= new DbUsersHelper(this);
     		db.addUser(u, check);
 			intent = new Intent(this,AdminActivity.class);
     	 }
    	
			startActivity(intent);
			break;
    	 
	 }
}
}