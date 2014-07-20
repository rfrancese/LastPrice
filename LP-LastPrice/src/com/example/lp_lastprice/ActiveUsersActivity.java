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
// Restituisce all'amministratore la lista di utenti attivi
public class ActiveUsersActivity extends ListActivity {
	String[] values ;
	DbUsersHelper db = new DbUsersHelper(this);
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    
	    	// mi metto nel contesto dell'activity
	    	int length= db.getUsers().length;

	    	values = new String[length] ;
	    	for (int i=0; i<length;i++){
	    		values[i]=db.getUsers()[i].getUser();
	    		
	    	
	    }    // use your custom layout
	 
		    // use your custom layout
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.activity_active_users, R.id.label_usr, values);
		    setListAdapter(adapter);
		  }

		  @Override
		  protected void onListItemClick(ListView l, View v, int position, long id) {
		    String item = (String) getListAdapter().getItem(position);
		    final int posizione=position;
		    
		    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		    new AlertDialog.Builder(this)
		    .setTitle("Elimina Utente")
		    .setMessage("Sei sicuro di voler eliminare questo utente?")
		    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	long i= db.deleteUser(values[posizione]);
		        	finish();
		        }
		     })
		    .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // do nothing
		        }
		     })
		    .setIcon(android.R.drawable.ic_dialog_alert)
		     .show();
		  }
		} 