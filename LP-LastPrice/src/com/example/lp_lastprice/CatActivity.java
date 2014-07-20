package com.example.lp_lastprice;
import android.app.ActionBar;
import android.view.MenuItem;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
// Action bar menu offerte 
public class CatActivity extends FragmentActivity implements
        ActionBar.TabListener {
	private int scelta=0;
	private String user="";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private Bundle bundle;
    // Tab titles
    private String[] tabs = { "Vacanze", "Benessere", "Sport","Svago","Ristoranti","Tecnologia","Last Price" };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
         bundle=getIntent().getExtras();
       scelta=bundle.getInt("scelta");
       user=bundle.getString("username");
     	if (scelta!=1)   Toast.makeText(this, "Accesso effettuato", Toast.LENGTH_LONG).show();
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
 
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
    public void openDetails(View view){
    	Intent intent=new Intent(this, DettagliActivity.class);
    	startActivity(intent);
    	
    }
   
    public int getAccess(){
    	return scelta;
    }
    public String getUser(){
    	return user;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.cat, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	String i=(String)item.getTitle() ;
    	if(i.compareTo("Rosso")==0)  getWindow().getDecorView().setBackgroundColor(android.graphics.Color.RED);
    	if(i.compareTo("Verde")==0)  getWindow().getDecorView().setBackgroundColor(android.graphics.Color.GREEN);
    	if(i.compareTo("Giallo")==0)  getWindow().getDecorView().setBackgroundColor(android.graphics.Color.YELLOW);
    	if(i.compareTo("Blue")==0)  getWindow().getDecorView().setBackgroundColor(android.graphics.Color.BLUE);
                return super.onOptionsItemSelected(item);
    	
    }
}