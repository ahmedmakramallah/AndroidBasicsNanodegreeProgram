package com.ahmedmakramallah.tourguideapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment {


    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.location_list, container, false);

        final ArrayList<CustomLocation> restaurantLocation = new ArrayList<CustomLocation>();
        restaurantLocation.add(new CustomLocation(getString(R.string.maestro_restaurant), getString(R.string.maestro_restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.zooba_restaurant), getString(R.string.zooba_restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.kazaz_restaurant), getString(R.string.kazaz_restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.el_fishawi_restaurant), getString(R.string.el_fishawi__restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.harrys_pub_restaurant), getString(R.string.harrys_pub_restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.lePacha_1901_restaurant), getString(R.string.lePacha_1901_restaurant_addr)));
        restaurantLocation.add(new CustomLocation(getString(R.string.the_tap_maadi_restaurant), getString(R.string.the_tap_maadi_restaurant_addr)));

        CustomLocationAdapter customLocationAdapter = new CustomLocationAdapter(getActivity(), restaurantLocation, R.color.category_restaurant);
        ListView restaurantListView = (ListView) rootView.findViewById(R.id.list);
        restaurantListView.setAdapter(customLocationAdapter);

        return rootView;
    }

}
