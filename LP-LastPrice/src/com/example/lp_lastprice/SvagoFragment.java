package com.example.lp_lastprice;

import database.DbUsersHelper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
// Categoria svago
public class SvagoFragment extends ListFragment {
	String[] descrizione=new String[100];
	String[] prezzo=new String[100];
	String[] luogo=new String[100];
	String[] venditore=new String[100];
	String[] latitudine=new String[100];
	String[] longitudine=new String[100];
	String[] telefono=new String[100];
	int scelta;
	String user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	String[] values ;
    	DbUsersHelper db = new DbUsersHelper(container.getContext());// mi metto nel contesto dell'activity
    	int length= db.getOffer("svago").length;
    	scelta=((CatActivity)getActivity()).getAccess();
    	user=((CatActivity)getActivity()).getUser();
    	if (scelta==1) Toast.makeText(getActivity(), "Devi effettuare il login", Toast.LENGTH_LONG).show();
    	else  Toast.makeText(getActivity(), "Accesso effettuato", Toast.LENGTH_LONG).show();
     values = new String[length] ;
    	for (int i=0; i<length;i++){
    		values[i]=db.getOffer("svago")[i].getName();
    		descrizione[i]=db.getOffer("svago")[i].getDesc();
    		prezzo[i]=db.getOffer("svago")[i].getPrice();
    		luogo[i]=db.getOffer("svago")[i].getPlace();
    		venditore[i]=db.getOffer("svago")[i].getVenditore();
    		latitudine[i]=db.getOffer("svago")[i].getLat();
    		longitudine[i]=db.getOffer("svago")[i].getLng();
    		
    	
    }  // use your custom layout
    	
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),R.layout.fragment_svago, R.id.svago_text,values );
    	    setListAdapter(adapter);
        View rootView = inflater.inflate(R.layout.fragment_svago, container, false);
         
        return rootView;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	if(scelta!=1){
    		Toast.makeText(getActivity(), "User: "+user, Toast.LENGTH_LONG).show();
    	Intent intent =new Intent(getActivity(),DettagliActivity.class);
    	intent.putExtra("descrizione", descrizione[position].toString());
	    intent.putExtra("prezzo", prezzo[position].toString());
	   intent.putExtra("luogo", luogo[position].toString());
	   intent.putExtra("venditore", venditore[position].toString());
	   intent.putExtra("latitudine", latitudine[position].toString());
	   intent.putExtra("longitudine", longitudine[position].toString());
	   intent.putExtra("username", user);
      startActivity(intent);
    	}
    	else Toast.makeText(getActivity(), "Devi effettuare il login per procedere all'acquisto", Toast.LENGTH_LONG).show();
    }
   
}