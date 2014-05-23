package com.example.lp_lastprice;

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
	        text_name.setText(bundle.getString("nome"));
	        text_lastname.setText(bundle.getString("cognome"));
	        text_birth.setText(bundle.getString("nascita"));
	        text_sex.setText(bundle.getString("user"));
	        text_user.setText(bundle.getString("pw"));
	        text_pass.setText(bundle.getString("sesso"));
	        text_iva.setText(bundle.getString("account"));
	        text_account.setText(bundle.getString("carta"));
	        account=bundle.getString("account");
	}
public void onClick(View view) {
	Intent intent=new Intent(this,WelcomeActivity.class);

	 switch ( view.getId() ) {
     case R.id.confirm:
    	 if (account.compareToIgnoreCase("v")==0){
    		
			intent = new Intent(this,SellerActivity.class);
    	 }
    	 if (account.compareToIgnoreCase("u")==0){
    		
 			intent = new Intent(this,WelcomeActivity.class);
     	 }
    	 if (account.compareToIgnoreCase("ad")==0){
    		
 			intent = new Intent(this,AdminActivity.class);
     	 }
    	
			startActivity(intent);
			break;
    	 
	 }
}
}