package com.example.lp_lastprice;
import android.widget.AdapterView.OnItemLongClickListener; 
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.DialogInterface;
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
import database.*;
//Programma  che restituisce la lista delle offerte attive di ciascun venditore
public class ActiveOffersActivity extends ListActivity implements OnItemLongClickListener {
	String[] descrizione=new String[100];
	String[] prezzo=new String[100];
	String[] luogo=new String[100];
	String[] venditore=new String[100];
	DbUsersHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String user=this.getIntent().getStringExtra("username");
		Toast.makeText(this, "User: "+user, Toast.LENGTH_LONG).show();
		db= new DbUsersHelper(this);
		if(db.getUserOffer(user)==null){
			Toast.makeText(this, "Nessun elemento trovato", Toast.LENGTH_LONG).show();
		}
		else{
			
		
		int length= db.getUserOffer(user).length;
		String[] values = new String[length] ;
    	for (int i=0; i<length;i++){
    		values[i]=db.getUserOffer(user)[i].getName();
    		descrizione[i]=db.getUserOffer(user)[i].getDesc();
    		prezzo[i]=db.getUserOffer(user)[i].getPrice();
    		luogo[i]=db.getUserOffer(user)[i].getPlace();
    		venditore[i]=db.getUserOffer(user)[i].getVenditore();
    	}
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
    	        R.layout.fragment_active_offers, R.id.label3, values);
    	    setListAdapter(adapter);
		}
		ListView lv=getListView();
		lv.setOnItemLongClickListener(this);
	}

	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	

    	Intent intent =new Intent(this,DettagliActivity.class);
    	intent.putExtra("descrizione", descrizione[position].toString());
	    intent.putExtra("prezzo", prezzo[position].toString());
	   intent.putExtra("luogo", luogo[position].toString());
	   intent.putExtra("venditore", venditore[position].toString());
      startActivity(intent);
    }
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v, int pos, long id){
		
		 final int posizione=pos;
		 new AlertDialog.Builder(this)
		    .setTitle("Elimina Offerta")
		    .setMessage("Sei sicuro di voler eliminare questa offerta?")
		    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        	long i= db.deleteOffer(descrizione[posizione]);
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
	


	return true;
}
}
