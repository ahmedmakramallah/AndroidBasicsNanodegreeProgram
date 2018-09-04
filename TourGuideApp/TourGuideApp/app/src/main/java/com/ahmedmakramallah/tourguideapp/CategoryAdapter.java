package com.ahmedmakramallah.tourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ahmed on 8/2/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    // Context of the app
    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }


    // Return the fragment that should be displayed for the given page number.

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new ClubFragment();
        }else if (position == 1){
            return new CinemaFragment();
        }else if (position == 2){
            return new HotelFragment();
        }else {
            return new RestaurantFragment();
        }
    }

    //Return the total number of pages.
    @Override
    public int getCount() {
        return 4;
    }

    // return the page title at the tab
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return mContext.getString(R.string.category_clubs);
        }else if (position == 1){
            return mContext.getString(R.string.category_cinemas);
        }else if (position == 2){
            return mContext.getString(R.string.category_hotels);
        }else {
            return mContext.getString(R.string.category_restaurants);
        }
    }
}
