package com.example.lp_lastprice;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;
import database.*;
import android.widget.Toast;
// Form richiesta rimborsi
public class RimborsoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_rimborso);
		
	}
		public void send(View view){
			DbUsersHelper db=new DbUsersHelper(this);
			EditText edit_user=(EditText)findViewById(R.id.user_form);
			EditText edit_seller=(EditText)findViewById(R.id.seller_form);
			EditText edit_causale=(EditText)findViewById(R.id.causale_form);
			String user=edit_user.getText().toString();
			String seller=edit_seller.getText().toString();
			String causale=edit_causale.getText().toString();
			Rimborso r=new Rimborso(user,seller,causale);
			long i= db.addRefund(r);
			Toast.makeText(this, "Richiesta inviata all'amministratore", Toast.LENGTH_LONG).show();
			finish();
			
		}
	
}
