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
// Finestra riepilogo dati registrazione
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
   private String telefono="";
   private String indirizzo="";
   private String mail="";
   private int check=0;
   private boolean b;
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
	       
	        final TextView text_telephone = (TextView) findViewById(R.id.telefono_r);
	        Bundle bundle = this.getIntent().getExtras();
	        nome=bundle.getString("nome");
	        cognome=bundle.getString("cognome");
	        nascita=bundle.getString("nascita");
	        user=bundle.getString("user");
	        sesso=bundle.getString("sesso");
	        password=bundle.getString("pw");
	        account=bundle.getString("account");
	        carta_iva=bundle.getString("carta");
	        telefono=bundle.getString("telefono");
	        mail=bundle.getString("mail");
	        indirizzo=bundle.getString("indirizzo");
	        text_name.setText(nome);
	        text_lastname.setText(cognome);
	        text_birth.setText(nascita);
	        text_sex.setText(sesso);
	        text_user.setText(user);
	        text_pass.setText(password);
	        text_iva.setText(carta_iva);
	        
	        text_telephone.setText(telefono);
	        account=bundle.getString("account");
	        
	}
public void onClick(View view) {
	Intent intent=new Intent(this,WelcomeActivity.class);
	long i=-1;
	Bundle bundle=new Bundle();
	 switch ( view.getId() ) {
     case R.id.confirm:
    	 if (account.compareToIgnoreCase("v")==0){
    		 check=2;
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva,telefono,mail,indirizzo);
    		 DbUsersHelper db= new DbUsersHelper(this);
    		
    		 
     		i=db.addUser(u, check);
     		Toast.makeText(this, "Seller n.ro: " + i, Toast.LENGTH_LONG).show();
			intent = new Intent(this,SellerActivity.class);
			bundle.putString("username", user);
			intent.putExtras(bundle);
    		 
    	 }
    	 if (account.compareToIgnoreCase("u")==0){
    		 check=1;// tipologia account utente
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva,telefono,mail,indirizzo);
    		DbUsersHelper db= new DbUsersHelper(this);
    		
    		i=db.addUser(u, check);
    		Toast.makeText(this, "Cliente n.ro: " + i, Toast.LENGTH_LONG).show();
 			intent = new Intent(this,WelcomeActivity.class);
 			bundle.putString("username", user);
			intent.putExtras(bundle);
     	 }
    	 if (account.compareToIgnoreCase("ad")==0){
    		 check=3;
    		 Utente u=new Utente(nome,cognome,nascita,sesso,user,password,carta_iva,telefono,mail,indirizzo);
    		 DbUsersHelper db= new DbUsersHelper(this);
     		i=db.addUser(u, check);
     		Toast.makeText(this, "Amministratore n.ro: " + i, Toast.LENGTH_LONG).show();
 			intent = new Intent(this,AdminActivity.class);
 			bundle.putString("username", user);
			intent.putExtras(bundle);
     	 }
    	 	
			startActivity(intent);
			finish();
			break;
    	 
	 }
}
}