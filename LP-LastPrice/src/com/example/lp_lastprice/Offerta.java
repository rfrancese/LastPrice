package com.example.lp_lastprice;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Offerta extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] values = new String[] { "Vacanze", "Benessere", "Sport",
        "Svago", "Ristoranti","Tecnologia", "LP-LastPrice" };
    // use your custom layout
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        R.layout.fragment_offerta, R.id.label, values);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
  }
} 