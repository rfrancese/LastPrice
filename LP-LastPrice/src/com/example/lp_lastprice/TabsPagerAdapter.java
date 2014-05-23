package com.example.lp_lastprice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
            return new HolidayFragment();
        case 1:
            // Games fragment activity
            return new WellnessFragment();
        case 2:
            // Movies fragment activity
            return new SportsFragment();
        case 3:
        	return new HolidayFragment();
        case 4:
        	return new SvagoFragment();
        case 5:
        	return new RestaurantFragment();
        case 6:
        	return new TecnologiaFragment();
        case 7:
        	return new LastPriceFragment();
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 7;
    }
 
}