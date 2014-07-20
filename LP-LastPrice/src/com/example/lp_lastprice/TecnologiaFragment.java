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
// Categoria tecnologia
public class TecnologiaFragment extends ListFragment {
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
    	int length= db.getOffer("tecnologia").length;
    	scelta=((CatActivity)getActivity()).getAccess();
    	user=((CatActivity)getActivity()).getUser();
    	
     values = new String[length] ;
    	for (int i=0; i<length;i++){
    		values[i]=db.getOffer("tecnologia")[i].getName();
    		descrizione[i]=db.getOffer("tecnologia")[i].getDesc();
    		prezzo[i]=db.getOffer("tecnologia")[i].getPrice();
    		luogo[i]=db.getOffer("tecnologia")[i].getPlace();
    		venditore[i]=db.getOffer("tecnologia")[i].getVenditore();
    		latitudine[i]=db.getOffer("tecnologia")[i].getLat();
    		longitudine[i]=db.getOffer("tecnologia")[i].getLng();
    		
    	
    }   // use your custom layout
    	
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),R.layout.fragment_tecnologia, R.id.tec_text,values );
    	    setListAdapter(adapter);
        View rootView = inflater.inflate(R.layout.fragment_tecnologia, container, false);
         
        return rootView;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	if(scelta!=1){
    		
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