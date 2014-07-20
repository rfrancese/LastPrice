package com.example.lp_lastprice;
import database.*;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.app.Activity;
import database.DbUsersHelper;
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
import android.widget.RadioButton;
import android.widget.Toast;
// Form di login
public class Login extends Activity {
	 public static final String MyPREFERENCES = "MyPrefs" ;
	 public static final String name = "nameKey"; 
	   public static final String pass = "passwordKey"; 
	   public static final String activity="1";
	   SharedPreferences sharedpreferences;	   
	private DbUsersHelper db=new DbUsersHelper(this);
	int n=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	 @Override
	   protected void onResume() {
		
	      sharedpreferences=getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
	      Intent i=new Intent(this,MainActivity.class);
	      
	      if (sharedpreferences.contains(name))
	    	  
	      {
	      if(sharedpreferences.contains(pass)){
	    	 
	    	  
	    	  if (n==1){
	    		  
	    		     i = new Intent(this,WelcomeActivity.class);
	    		         startActivity(i);
	    		    	 }
	    	  else if (n==2){
	     i = new Intent(this,SellerActivity.class);
	         startActivity(i);
	    	 }
	    	 else if (n==3){
	    		  i = new Intent(this,AdminActivity.class);
	 	         startActivity(i); 
	    	 }
	      }
	      else {
	    	  i=new Intent(this,MainActivity.class);
	    	  startActivity(i);
	      }
	      }
	      super.onResume();
	   }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}
	public void accesso(View view){
	     Editor editor = sharedpreferences.edit();
	   
	      
		EditText edit_user=(EditText)findViewById(R.id.username);
		EditText edit_password=(EditText)findViewById(R.id.password);
		RadioButton cliente = (RadioButton)findViewById(R.id.cliente);
		RadioButton venditore = (RadioButton)findViewById(R.id.venditore);
		RadioButton amministratore = (RadioButton)findViewById(R.id.amministratore);
		String u = edit_user.getText().toString();
		String p = edit_password.getText().toString();
		editor.putString(name, u);
		editor.putString(pass, p);
	
		
		if(cliente.isChecked()) n=1;
		if(venditore.isChecked())n=2;
		if(amministratore.isChecked())n=3;
		String user=edit_user.getText().toString();
		String password=edit_password.getText().toString();
	
		if(n==1){
		boolean b=db.searchUser(user, password, n);
		if(b){
			Bundle bundle=new Bundle();
  		  bundle.putString("username", u);
		Intent intent = new Intent(this, WelcomeActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
		}
		
		else  Toast.makeText(this, "Impossibile Accedere", Toast.LENGTH_LONG).show();
	}
		if (n==2) {
			boolean b=db.searchUser(user, password, n);
			if(b){
				Bundle bundle=new Bundle();
		  		  bundle.putString("username", u);
			Intent intent =new Intent(this, SellerActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			}
			else  Toast.makeText(this, "Impossibile Accedere Come Seller", Toast.LENGTH_LONG).show();
		}
		if(n==3){
			boolean b=db.searchUser(user, password, n);
			if(b){
				
			
			Intent intent =new Intent(this, AdminActivity.class);
			startActivity(intent);
			}
			else  Toast.makeText(this, "Impossibile Accedere Come Admin", Toast.LENGTH_LONG).show();
		}
		editor.commit();
		}
	public void registrati(View view){
		Intent intent = new Intent(this, Registrazione.class);
		startActivity(intent);
	}

}
