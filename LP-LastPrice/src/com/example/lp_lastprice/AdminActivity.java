package com.example.lp_lastprice;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.app.Activity;

import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;
// Home amministratore
public class AdminActivity extends Activity {
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
		setContentView(R.layout.fragment_admin);

	  }
	  public void refunds(View view){
		  Intent intent= new Intent(this,RefundsActivity.class);
		  startActivity(intent);
	  }
	  public void deleteUser(View view){
		  Intent intent=new Intent (this,ActiveUsersActivity.class);
		  startActivity(intent);
	  }
	  
	 public void getTransaction(View view){
		 Intent intent = new Intent(this, TransactionActivity.class);
		 startActivity(intent);
	 }
	 public void logout(View view){
	      SharedPreferences sharedpreferences = getSharedPreferences
	      (Login.MyPREFERENCES, MODE_PRIVATE);
	      Editor editor = sharedpreferences.edit();
	      editor.clear();
	      editor.commit();
	     
	      moveTaskToBack(true); 
	     AdminActivity.this.finish();
	   }
	 @Override
	 public void onDestroy(){
		
		    SharedPreferences sharedpreferences = getSharedPreferences
				      (Login.MyPREFERENCES, MODE_PRIVATE);
				      Editor editor = sharedpreferences.edit();
				      editor.clear();
				      editor.commit();
				      super.onDestroy();
	 }
	 @Override
	 public void onPause(){
		
		    SharedPreferences sharedpreferences = getSharedPreferences
				      (Login.MyPREFERENCES, MODE_PRIVATE);
				      Editor editor = sharedpreferences.edit();
				      editor.clear();
				      editor.commit();
				      super.onPause();
	 }

}