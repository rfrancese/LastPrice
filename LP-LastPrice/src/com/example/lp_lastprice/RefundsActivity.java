package com.example.lp_lastprice;
import database.DbUsersHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;
import android.content.DialogInterface;
// Lista richiesta rimborsi
public class RefundsActivity extends ListActivity {
	String[] values ;
	String [] user;
	String [] seller;
	DbUsersHelper db = new DbUsersHelper(this);
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    
	    	// mi metto nel contesto dell'activity
	    	int length= db.getRefunds().length;

	    	values = new String[length] ;
	    	user=new String[length];
	    	seller=new String[length];
	    	for (int i=0; i<length;i++){
	    		values[i]=db.getRefunds()[i].getCaus();
	    		user[i]=db.getRefunds()[i].getUser();
	    		seller[i]=db.getRefunds()[i].getSeller();
	    	
	    }    // use your custom layout
	 
		    // use your custom layout
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.activity_refunds, R.id.label_usr, values);
		    setListAdapter(adapter);
		  }

		  @Override
		  protected void onListItemClick(ListView l, View v, int position, long id) {
			  Uri uri = Uri.parse("https://mail.google.com/mail/");
				Toast.makeText(this, "Richiesto da: " + user[position]+ " Seller: "+seller[position], Toast.LENGTH_LONG).show();
		        startActivity(new Intent( Intent.ACTION_VIEW, uri));
		    
		} 
}