package com.example.lp_lastprice;
import database.*;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import database.*;
// Activity estrazione premio
public class LastPriceActivity extends Activity {
  String f="";
  Premio premio;
  String user;
  double credito;
  double min;
  double max;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lastprice);
		Intent intent = getIntent();
		 f=intent.getStringExtra("fascia");
		 String parti[]=f.split("-");
		 min=Double.parseDouble(parti[0]);
		 max=Double.parseDouble(parti[1]);
		 user=intent.getStringExtra("username");
		 credito=intent.getDoubleExtra("credito", 0);
		 premio=new Premio();

}
	public void estrai(View view){
		DbUsersHelper db=new DbUsersHelper(this);
		String mess="";
		if((credito>(min))&&(credito<(max))){
		mess=premio.estrai(f);
		TextView mess1=(TextView)findViewById(R.id.Messaggio_v_p);
		mess1.setVisibility(View.VISIBLE);
		
		TextView mess2=(TextView)findViewById(R.id.Messaggio_premio);
		mess2.setText(mess);
		mess2.setVisibility(View.VISIBLE);
		double b=db.updateCredit(user);
		
		}
		else if(credito<0) Toast.makeText(this, "Si è verificato un errore", Toast.LENGTH_LONG).show();
		else Toast.makeText(this, "Non hai speso abbastanza per estrarre il premio in questa fascia", Toast.LENGTH_LONG).show();
		
		
	}
}
