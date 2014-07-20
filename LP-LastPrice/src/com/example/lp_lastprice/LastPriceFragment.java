package com.example.lp_lastprice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import database.*;
// Categoria LastPrice per la divisione in fasce di credito
public class LastPriceFragment extends ListFragment {
 String fascia="500-1000";
 String fascia1="1000-2000";
 String fascia2="2000-5000";
 String user;
 DbUsersHelper db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	String[] values = new String[] { fascia,fascia1,fascia2 };
	    // use your custom layout
ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(),R.layout.fragment_last_price, R.id.last_text, values);
	    setListAdapter(adapter);
        View rootView = inflater.inflate(R.layout.fragment_last_price, container, false);
        user=((CatActivity)getActivity()).getUser();
        db=new DbUsersHelper(container.getContext());
        return rootView;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
      String item = (String) getListAdapter().getItem(position);
      Double credito=db.getCredito(user);

      Toast.makeText(getActivity(), "Credito attuale: " + credito, Toast.LENGTH_LONG).show();
      Intent intent =new Intent(getActivity(),LastPriceActivity.class);
      intent.putExtra("fascia", item.toString());
      intent.putExtra("username",user);
      intent.putExtra("credito", credito);
      startActivity(intent);
    }
}