package com.example.lp_lastprice;
import database.DbUsersHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
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
// Lista acquisti effettuati dagli utenti visualizzabili dall'amministratore
public class TransactionActivity extends ListActivity {
	String[] values ;
	DbUsersHelper db = new DbUsersHelper(this);
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    
	    	// mi metto nel contesto dell'activity
	    	int length= db.getSales().length;

	    	values = new String[length] ;
	    	for (int i=0; i<length;i++){
	    		values[i]=db.getSales()[i].getDescr();
	    		
	    	
	    }    // use your custom layout
	 
		    // use your custom layout
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.activity_transaction, R.id.label_usr, values);
		    setListAdapter(adapter);
		  }

		  @Override
		  protected void onListItemClick(ListView l, View v, int position, long id) {
		    String item = (String) getListAdapter().getItem(position);
		    String descr=item;
		    DbUsersHelper db=new DbUsersHelper(this);
		    String seller=db.getSellerSale(descr);
		    int utenti = db.getUserSale(descr);
		    
		    Toast.makeText(this, "Acquistato da: " + utenti + " utenti ," +" venduto da: " + seller, Toast.LENGTH_LONG).show();
		   
		  }
		} 